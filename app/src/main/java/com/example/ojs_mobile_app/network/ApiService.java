package com.example.ojs_mobile_app.network;

import com.example.ojs_mobile_app.models.Journal;
import com.example.ojs_mobile_app.models.Issue;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("journals.php")
    Call<List<Journal>> getJournals();

    @GET("issues.php")
    Call<List<Issue>> getIssues(@Query("j_id") int journalId);
}