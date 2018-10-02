package iot.nvipash_harvardapi.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import iot.nvipash_harvardapi.entities.Records;
import iot.nvipash_harvardapi.R;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder> {

    private ArrayList<Records> recordsList;

    public RecordsAdapter(ArrayList<Records> recordsList) {
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public RecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parentView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parentView.getContext());
        View view = layoutInflater.inflate(R.layout.single_record_view, parentView, false);
        return new RecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsViewHolder viewHolder, int position) {
        viewHolder.recordTitle.setText(recordsList.get(position).getTitle());
        viewHolder.recordTechnique.setText(recordsList.get(position).getTechnique());
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    protected class RecordsViewHolder extends RecyclerView.ViewHolder {
        private TextView recordTitle;
        private TextView recordTechnique;

        private RecordsViewHolder(View itemView) {
            super(itemView);
            recordTitle = itemView.findViewById(R.id.api_record_title);
            recordTechnique = itemView.findViewById(R.id.api_record_technique);
        }
    }
}