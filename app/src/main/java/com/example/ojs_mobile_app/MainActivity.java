package com.example.ojs_mobile_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ojs_mobile_app.adapters.JournalAdapter;
import com.example.ojs_mobile_app.models.Journal;
import com.example.ojs_mobile_app.network.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JournalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchJournals();
    }

    private void fetchJournals() {
        RetrofitClient.getApiService().getJournals().enqueue(new Callback<List<Journal>>() {
            @Override
            public void onResponse(Call<List<Journal>> call, Response<List<Journal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new JournalAdapter(MainActivity.this, response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Journal>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }
}
