package com.example.ojs_mobile_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojs_mobile_app.adapters.VolumeAdapter;
import com.example.ojs_mobile_app.models.Issue;
import com.example.ojs_mobile_app.network.ApiService;
import com.example.ojs_mobile_app.network.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VolumesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView titleTextView;
    private VolumeAdapter adapter;
    private int journalId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumes);

        recyclerView = findViewById(R.id.recyclerVolumes);
        progressBar = findViewById(R.id.progressBar);
        titleTextView = findViewById(R.id.txtJournalTitle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        journalId = getIntent().getIntExtra("journal_id", -1);
        String journalTitle = getIntent().getStringExtra("journal_title");

        if (journalId == -1) {
            Toast.makeText(this, "Error: No se pudo cargar la revista", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        titleTextView.setText(journalTitle);

        loadVolumes();
    }

    private void loadVolumes() {
        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = RetrofitClient.getApiService();

        Call<List<Issue>> call = apiService.getIssues(journalId);

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Issue> volumeList = response.body();
                    adapter = new VolumeAdapter(VolumesActivity.this, volumeList);
                    recyclerView.setAdapter(adapter);
                } else if (response.code() == 404) {
                    Toast.makeText(VolumesActivity.this, "No se encontraron volúmenes para esta revista.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VolumesActivity.this, "Error al cargar volúmenes.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("VolumesActivity", "Error de conexión: " + t.getMessage());
                String errorMessage = "Error al cargar volúmenes. Inténtalo de nuevo.";
                if (t instanceof IOException) {
                    errorMessage = "Error de conexión. Verifica tu conexión a internet.";
                }
                Toast.makeText(VolumesActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}