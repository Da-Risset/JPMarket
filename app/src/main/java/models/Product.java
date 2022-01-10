package models;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("title")
    private String title;
    @SerializedName("suplier")
    private String suplier;
    @SerializedName("published")
    private String published;
    @SerializedName("price")
    private Integer price;
    @SerializedName("description")
    private String description;
    @SerializedName("website")
    private String website;
    @SerializedName("thumbnail")
    private String thumbnail;

    public Product() {
    }

    public Product(String title, String suplier, String published, Integer price, String description, String website, String thumbnail) {
        this.title = title;
        this.suplier = suplier;
        this.published = published;
        this.price = price;
        this.description = description;
        this.website = website;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuplier() {
        return suplier;
    }

    public void setSuplier(String suplier) {
        this.suplier = suplier;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
