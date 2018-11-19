package iot.nvipash_harvardapi.entities;

import com.google.gson.annotations.SerializedName;

public class Record {
    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("creditline")
    private String creditLine;

    @SerializedName("dimensions")
    private String dimensions;

    @SerializedName("department")
    private String department;

    @SerializedName("provenance")
    private String provenance;

    @SerializedName("technique")
    private String technique;

    @SerializedName("primaryimageurl")
    private String primaryImageUrl;

    public Record(Integer id, String title, String creditLine,
                  String dimensions, String department, String provenance,
                  String technique, String primaryImageUrl) {
        this.id = id;
        this.title = title;
        this.creditLine = creditLine;
        this.dimensions = dimensions;
        this.department = department;
        this.provenance = provenance;
        this.technique = technique;
        this.primaryImageUrl = primaryImageUrl;
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

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }
}