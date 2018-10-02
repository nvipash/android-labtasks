package iot.nvipash_harvardapi.entities;

import com.google.gson.annotations.SerializedName;

public class Records {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("technique")
    private String technique;

    public Records(Integer id, String title, String technique) {
        this.id = id;
        this.title = title;
        this.technique = technique;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }
}