package iot.nvipash_harvardapi.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.R;
import iot.nvipash_harvardapi.activities.MainActivity;
import iot.nvipash_harvardapi.entities.Record;

public class RecordsFavouritesFragment extends Fragment {
    private ArrayList<Record> recordList;

    @BindView(R.id.recycler_view_records_list)
    protected RecyclerView recordsListView;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_records, container, false);
        ButterKnife.bind(this, view);

        ((MainActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.favourites_title);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        recordList = new ArrayList<>();
        setFavouritesFromPreferences();
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .generateRecordsList(recordList, recordsListView);
    }

    private void setFavouritesFromPreferences() {
        SharedPreferences favouritesPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(RecordDetailsFragment.FAVOURITE_RECORD, Context.MODE_PRIVATE);
        Map<String, ?> favouritesPreferencesAll = favouritesPreferences.getAll();

        for (Map.Entry<String, ?> entry : favouritesPreferencesAll.entrySet()) {
            recordList.add(new Gson().fromJson(entry.getValue().toString(), Record.class));
        }

        if (recordList.isEmpty()) {
            ((MainActivity) Objects.requireNonNull(getActivity()))
                    .showSnackBar(R.string.empty_favourites_info);
        }
    }
}
