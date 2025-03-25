package com.example.ojs_mobile_app.models;

import com.google.gson.annotations.SerializedName;

public class Journal {

    @SerializedName("journal_id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("portada")
    private String coverUrl; // URL de la imagen de portada

    public int getId() { return id; }

    public String getName() { return name; }

    public String getCoverUrl() { return coverUrl; }

    public String getTitle() {
        return name; // Asumiendo que "name" contiene el título del artículo
    }

    public String getImageUrl() {
        return coverUrl; // Asumiendo que "coverUrl" contiene el URL de la imagen.
    }
}
