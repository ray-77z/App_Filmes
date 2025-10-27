package com.example.myapplicationfilmespiderman;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationfilmespiderman.Adapter.MovieAdapter;
import com.example.myapplicationfilmespiderman.Api.ApiService;
import com.example.myapplicationfilmespiderman.Api.RetrofitClient;
import com.example.myapplicationfilmespiderman.model.Movie;
import com.example.myapplicationfilmespiderman.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "5be5dd1552396c13dc52e78ec8f1c320";
    private static final String PREFS = "filmes_prefs";
    private static final String KEY_LAST = "last_search";

    private EditText editTextSearch;
    private Button buttonSearch;
    private RecyclerView recyclerViewMovies;
    private ProgressBar progressBar;

    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter adapter;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewMovies = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String last = prefs.getString(KEY_LAST, "");
        editTextSearch.setText(last);

        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(this, movieList);
        recyclerViewMovies.setAdapter(adapter);

        buttonSearch.setOnClickListener(v -> {
            String q = editTextSearch.getText().toString().trim();
            if (q.isEmpty()) {
                Toast.makeText(this, "Digite o nome de um filme!", Toast.LENGTH_SHORT).show();
                return;
            }
            prefs.edit().putString(KEY_LAST, q).apply();
            buscarFilmes(q);
        });

        if (!last.isEmpty()) buscarFilmes(last);
    }

    private void buscarFilmes(String query) {
        progressBar.setVisibility(View.VISIBLE);
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        Call<MovieResponse> call = api.getMovies(API_KEY, query);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null && response.body().getResults() != null) {
                    List<Movie> results = response.body().getResults();
                    movieList.clear();
                    movieList.addAll(results);
                    adapter.notifyDataSetChanged();
                } else {
                    movieList.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Nenhum filme encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
