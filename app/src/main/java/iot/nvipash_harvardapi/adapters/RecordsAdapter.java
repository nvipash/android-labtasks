package iot.nvipash_harvardapi.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import iot.nvipash_harvardapi.R;
import iot.nvipash_harvardapi.activities.MainActivity;
import iot.nvipash_harvardapi.entities.Record;
import iot.nvipash_harvardapi.fragments.RecordDetailsFragment;
import iot.nvipash_harvardapi.views.RecordsViewHolder;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Record item);
    }

    private final OnItemClickListener listener;
    private ArrayList<Record> recordList;

    public RecordsAdapter(ArrayList<Record> recordList,
                          OnItemClickListener listener) {
        this.recordList = recordList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parentView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parentView.getContext());
        View view = layoutInflater.inflate(R.layout.single_record_view, parentView, false);
        return new RecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsViewHolder viewHolder,
                                 @SuppressLint("RecyclerView") final int position) {
        viewHolder.recordsTitle
                .setText(recordList.get(position).getTitle());
        viewHolder.recordsCreditLine
                .setText(recordList.get(position).getCreditLine());
        viewHolder.recordsDimensions
                .setText(recordList.get(position).getDimensions());
        Picasso.with(viewHolder.itemView.getContext())
                .load(recordList.get(position).getPrimaryImageUrl())
                .centerCrop().fit().into(viewHolder.recordsPrimaryImage);

        viewHolder.detailsButton.setOnClickListener(view ->
                openDetailsFragment(position, view)
        );
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    private void openDetailsFragment(int position, View view) {
        RecordDetailsFragment detailsFragment = new RecordDetailsFragment();
        Bundle bundleId = new Bundle();
        bundleId.putInt("RECORDS_ID", recordList.get(position).getId());
        detailsFragment.setArguments(bundleId);
        listener.onItemClick(recordList.get(position));
        ((MainActivity) Objects.requireNonNull(view.getContext()))
                .getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment, null)
                .addToBackStack(null).commit();
    }
}