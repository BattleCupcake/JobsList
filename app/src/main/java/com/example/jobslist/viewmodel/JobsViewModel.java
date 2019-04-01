package com.example.jobslist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.jobslist.database.Employes;
import com.example.jobslist.repository.JobsRepository;
import java.util.List;

public class JobsViewModel extends AndroidViewModel {

    private JobsRepository jobsRepository;
    private LiveData<List<String>> specialtyName;
    private LiveData<List<Employes>> specialtyWorker;
    private LiveData<List<Employes>> workerId;

    public JobsViewModel(Application application) {
        super(application);
        jobsRepository = new JobsRepository(application);
        specialtyName = jobsRepository.getSpecialtyName();
    }

    public LiveData<List<Employes>> getSpecialtyWorker(String specialty) {
        specialtyWorker = jobsRepository.getSpecialtyWorker(specialty);
        return specialtyWorker;
    }

    public LiveData<List<Employes>> getWorkerId(int id) {
        workerId = jobsRepository.getWorkerId(id);
        return workerId;
    }

    public LiveData<List<String>> getSpecialtyName() {
        return specialtyName;
    }

    public void getLoadDataInternet() {
        jobsRepository.loadDataNetwork();
    }

    public void insert(Employes jobs) {
        jobsRepository.insert(jobs);
    }

    public void insertEmployes(Employes... employes) {
        jobsRepository.insertEmployes(employes);
    }
}