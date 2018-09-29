package iot.nvipash_harvardapi.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecordsList {
    @SerializedName("records")
    private ArrayList<Records> records;

    public ArrayList<Records> getRecordsArrayList() {
        return records;
    }

    public void setRecordsArrayList(ArrayList<Records> recordsArrayList) {
        this.records = recordsArrayList;
    }
}
