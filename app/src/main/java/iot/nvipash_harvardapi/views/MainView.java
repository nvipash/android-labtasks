package iot.nvipash_harvardapi.views;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import iot.nvipash_harvardapi.entities.Record;

public interface MainView {
    void setFragment(final Fragment fragment);

    void showSnackBar(int textFromResources);

    void generateRecordsList(ArrayList<Record> recordList,
                             RecyclerView recordsListView);
}
