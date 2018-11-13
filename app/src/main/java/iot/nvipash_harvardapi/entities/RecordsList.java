package iot.nvipash_harvardapi.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecordsList {
    @SerializedName("records")
    private ArrayList<Record> records;

    public ArrayList<Record> getRecordsArrayList() {
        return records;
    }

    public void setRecordsArrayList(ArrayList<Record> recordArrayList) {
        this.records = recordArrayList;
    }
}