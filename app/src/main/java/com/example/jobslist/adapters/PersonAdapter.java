package com.example.jobslist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jobslist.R;
import com.example.jobslist.database.Employes;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvF_name;
        private final TextView tvL_name;
        private final TextView tvAge;

        private WordViewHolder(View itemView) {
            super(itemView);
            this.tvF_name = itemView.findViewById(R.id.tvF_name);
            this.tvL_name = itemView.findViewById(R.id.tvL_name);
            this.tvAge = itemView.findViewById(R.id.tvAge);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Employes item);
    }

    private final LayoutInflater mInflater;
    private List<Employes> mEmpliyes;
    private final OnItemClickListener listener;

    public PersonAdapter(Context context, OnItemClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_person, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mEmpliyes.get(position));
            }
        });
        if (mEmpliyes != null) {

            Employes current = mEmpliyes.get(position);
            holder.tvL_name.setText(current.getL_name());
            holder.tvF_name.setText(current.getF_name());
            String s = current.getAge();
            if (!s.equals("-")) {
                holder.tvAge.setText(s);
            } else {
                holder.tvAge.setText("-");
            }
        }
    }

    public void setJob(List<Employes> employes) {
        mEmpliyes = employes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mEmpliyes != null)
            return mEmpliyes.size();
        else return 0;
    }
}