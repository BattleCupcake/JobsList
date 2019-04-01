package com.example.jobslist.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jobslist.R;
import com.example.jobslist.database.Employes;
import com.example.jobslist.viewmodel.JobsViewModel;
import com.example.jobslist.viewmodel.SharedViewModel;

import java.text.ParseException;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    private JobsViewModel jobsViewModel;
    private SharedViewModel modelShare;

    @BindView(R.id.tvF_name)
    TextView tvF_name;

    @BindView(R.id.tvL_name)
    TextView tvL_name;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvBirthday)
    TextView tvBirthday;

    @BindView(R.id.tvAge)
    TextView tvAge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        modelShare = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        jobsViewModel = ViewModelProviders.of(this).get(JobsViewModel.class);
        modelShare.getId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer id) {
                jobsViewModel.getWorkerId(id).observe(getActivity(), new Observer<List<Employes>>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onChanged(@Nullable List<Employes> employes) {
                        tvF_name.setText(employes.get(0).getF_name());
                        tvL_name.setText(employes.get(0).getL_name());
                        tvName.setText(employes.get(0).getName());
                        tvBirthday.setText(employes.get(0).getBirthday());
                        tvAge.setText(employes.get(0).getAge());

                    }
                });
            }
        });
        return view;
    }

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }
}
