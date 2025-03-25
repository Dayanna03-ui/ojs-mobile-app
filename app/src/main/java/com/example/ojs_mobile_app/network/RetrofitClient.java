package com.example.ojs_mobile_app.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://revistas.uteq.edu.ec/ws/";
    private static Retrofit retrofit = null;
    private static ApiService apiService = null; // Agregamos apiService como variable estática

    public static ApiService getApiService() {
        if (apiService == null) { // Verificamos si apiService es null
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            apiService = retrofit.create(ApiService.class); // Inicializamos apiService
        }
        return apiService; // Retornamos la instancia de apiService
    }
}