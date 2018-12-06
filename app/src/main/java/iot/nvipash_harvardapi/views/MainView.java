package iot.nvipash_harvardapi.views;

import java.util.List;

import iot.nvipash_harvardapi.entities.Record;

public interface MainView {
    void setDataToRecyclerView(List<Record> recordList);
    void refreshData(List<Record> records);
    void onResponseFailure(Throwable throwable);
}
