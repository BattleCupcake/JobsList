package com.example.jobslist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobslist.R;
import com.example.jobslist.fragments.JobsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            JobsFragment jobsFragment = new JobsFragment();
            jobsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment1, jobsFragment).commit();
        }
    }
}
