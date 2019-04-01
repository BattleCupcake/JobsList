package com.example.jobslist.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jobslist.R;
import com.example.jobslist.adapters.JobsAdapter;
import com.example.jobslist.viewmodel.JobsViewModel;
import com.example.jobslist.viewmodel.SharedViewModel;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JobsFragment extends Fragment {

    @BindView(R.id.specialityRecyclerView)
    RecyclerView recyclerView;
    private JobsViewModel jobsViewModel;
    private SharedViewModel modelShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static JobsFragment newInstance() {
        return new JobsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);
        ButterKnife.bind(this, view);
        modelShare = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        final JobsAdapter adapter = new JobsAdapter(getContext(),
                new JobsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String item) {
                        modelShare.setSpecialityNae(item);
                        PersonFragment nextFrag = new PersonFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment2, nextFrag)
                                .addToBackStack(null)
                                .commit();
                    }
                });
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        jobsViewModel = ViewModelProviders.of(this).get(JobsViewModel.class);
        jobsViewModel.getSpecialtyName().observe(getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> specialty) {
                if (specialty.isEmpty()) {
                    jobsViewModel.getLoadDataInternet();
                } else {
                    adapter.setJob(specialty);
                }
            }
        });
        return view;
    }
}
