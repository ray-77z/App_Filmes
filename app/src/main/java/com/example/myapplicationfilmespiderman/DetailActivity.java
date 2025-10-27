package com.example.myapplicationfilmespiderman;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView imagePoster;
    private TextView textTitle, textYear, textDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imagePoster = findViewById(R.id.imagePoster);
        textTitle = findViewById(R.id.textTitle);
        textYear = findViewById(R.id.textYear);
        textDesc = findViewById(R.id.textDesc);

        String title = getIntent().getStringExtra("title");
        String year = getIntent().getStringExtra("year");
        String poster = getIntent().getStringExtra("poster");
        String description = getIntent().getStringExtra("description");

        textTitle.setText(title != null ? title : "—");
        textYear.setText(year != null ? year : "");
        textDesc.setText(description != null ? description : "Sem descrição.");

        Glide.with(this)
                .load(poster)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imagePoster);
    }
}
