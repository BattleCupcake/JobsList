package com.example.jobslist.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jobslist.R;
import com.example.jobslist.adapters.PersonAdapter;
import com.example.jobslist.database.Employes;
import com.example.jobslist.viewmodel.JobsViewModel;
import com.example.jobslist.viewmodel.SharedViewModel;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonFragment extends Fragment {

    @BindView(R.id.workerRecyclerView)
    RecyclerView recyclerView;
    private JobsViewModel jobsViewModel;
    private SharedViewModel modelShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        final PersonAdapter adapter = new PersonAdapter(getContext(),
                new PersonAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Employes item) {
                        modelShare.setId(item.getId());
                        DetailsFragment nextFrag = new DetailsFragment();
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
        modelShare = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        jobsViewModel = ViewModelProviders.of(this).get(JobsViewModel.class);
        modelShare.getSpecialityName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String specialityName) {
                jobsViewModel.getSpecialtyWorker(specialityName).observe(getActivity(), new Observer<List<Employes>>() {
                    @Override
                    public void onChanged(@Nullable List<Employes> employes) {
                        adapter.setJob(employes);
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
