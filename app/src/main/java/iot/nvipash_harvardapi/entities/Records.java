package iot.nvipash_harvardapi.entities;

import com.google.gson.annotations.SerializedName;

public class Records {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("creditline")
    private String creditLine;
    @SerializedName("dimensions")
    private String dimensions;
    @SerializedName("primaryimageurl")
    private String primaryImageUrl;

    public Records(Integer id, String title, String creditLine,
                   String dimensions, String primaryImageUrl) {
        this.id = id;
        this.title = title;
        this.creditLine = creditLine;
        this.dimensions = dimensions;
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

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }
}