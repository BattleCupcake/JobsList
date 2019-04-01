package com.example.jobslist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jobslist.R;
import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {

    class JobsViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordStandartView;

        private JobsViewHolder(View itemView) {
            super(itemView);
            wordStandartView = itemView.findViewById(R.id.tvSpeciality);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    private final LayoutInflater mInflater;
    private List<String> mJobs;
    private final OnItemClickListener listener;

    public JobsAdapter(Context context, OnItemClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_job, parent, false);
        return new JobsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobsViewHolder holder, final int position) {
        holder.wordStandartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mJobs.get(position));
            }
        });

        if (mJobs != null) {
            String current = mJobs.get(position);
            holder.wordStandartView.setText(current.toString());
        } else {
            holder.wordStandartView.setText(R.string.no_employes);
        }
    }

    public void setJob(List<String> specialty) {
        mJobs = specialty;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mJobs != null)
            return mJobs.size();
        else return 0;
    }
}