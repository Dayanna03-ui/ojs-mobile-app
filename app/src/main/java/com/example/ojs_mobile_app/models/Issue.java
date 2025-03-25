package com.example.ojs_mobile_app.models;

import com.google.gson.annotations.SerializedName;

public class Issue {

    @SerializedName("issue_id")
    private int id;

    @SerializedName("title")
    private String title;

    private String date;

    @SerializedName("volume")
    private int volume;

    @SerializedName("number")
    private int number;

    @SerializedName("year")
    private int year;

    @SerializedName("period")
    private String period;

    @SerializedName("cover") // Asumiendo que "cover" es el campo que almacena la URL de la imagen
    private String imageUrl;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getVolume() {
        return volume;
    }

    public int getNumber() {
        return number;
    }

    public int getYear() {
        return year;
    }

    public String getPeriod() {
        return period;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}