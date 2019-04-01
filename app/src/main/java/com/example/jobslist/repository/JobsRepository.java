package com.example.jobslist.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.example.jobslist.database.Employes;
import com.example.jobslist.database.EmployesDao;
import com.example.jobslist.database.EmployesRoomDatabase;
import com.example.jobslist.models.PersonsList;
import com.example.jobslist.network.RetrofitOkhttp;
import com.example.jobslist.network.api;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsRepository {

    private EmployesDao mJobsDao;
    private LiveData<List<String>> specialtyName;
    private LiveData<List<Employes>> specialtyWorker;
    private LiveData<List<Employes>> workerId;

    public JobsRepository(Application application) {
        EmployesRoomDatabase db = EmployesRoomDatabase.getDatabase(application);
        mJobsDao = db.employesDao();
        specialtyName = mJobsDao.getSpecialtyName();
    }

    public void loadDataNetwork() {
        RetrofitOkhttp RetrofitOkhttp = new RetrofitOkhttp();
        api api = RetrofitOkhttp.createService(api.class);
        Call<PersonsList> call = api.getData();
        call.enqueue(new Callback<PersonsList>() {
            @Override
            public void onResponse(Call<PersonsList> call, Response<PersonsList> response) {
                PersonsList employesModelList = response.body();
                for (int i = 0; i < employesModelList.getEmploye().size(); i++) {
                    String f_name = employesModelList.getEmploye().get(i).getF_name();
                    String l_name = employesModelList.getEmploye().get(i).getL_name();
                    String birthday = employesModelList.getEmploye().get(i).getBirthday();
                    String avatr_url = employesModelList.getEmploye().get(i).getAvatr_url();
                    String specialty_id = employesModelList.getEmploye().get(i).getSpecialty().get(0).getSpecialty_id();
                    String name = employesModelList.getEmploye().get(i).getSpecialty().get(0).getName();
                    Employes questions = new Employes(f_name, l_name,
                            birthday, avatr_url, specialty_id, name);
                    insert(questions);
                }
            }

            @Override
            public void onFailure(Call<PersonsList> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Employes>> getSpecialtyWorker(String specialty) {
        return mJobsDao.getSpecialtyWorker(specialty);
    }

    public LiveData<List<Employes>> getWorkerId(int id) {
        return mJobsDao.getId(id);
    }

    public LiveData<List<String>> getSpecialtyName() {
        return specialtyName;
    }

    public void insert(Employes jobs) {
        new insertAsyncTask(mJobsDao).execute(jobs);
    }

    public void insertEmployes(Employes... employes) {
        new insertEmployesAsyncTask(mJobsDao).execute(employes);
    }

    private static class insertEmployesAsyncTask extends AsyncTask<Employes, Void, Void> {
        private EmployesDao mAsyncTaskDao;

        insertEmployesAsyncTask(EmployesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employes... params) {
            mAsyncTaskDao.insertEmployes(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Employes, Void, Void> {
        private EmployesDao mAsyncTaskDao;

        insertAsyncTask(EmployesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employes... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}