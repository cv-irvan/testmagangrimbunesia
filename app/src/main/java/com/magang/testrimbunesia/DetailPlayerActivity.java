package com.magang.testrimbunesia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailPlayerActivity extends AppCompatActivity {
    public static String EXTRA_PLAYER = "extra_player";
    TextView tvNo, tvNama, tvCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);

        tvNo = findViewById(R.id.tvNo);
        tvNama = findViewById(R.id.tvNama);
        tvCategory = findViewById(R.id.tvCategory);

        PlayerItem playerItem =  getIntent().getParcelableExtra(EXTRA_PLAYER);

        String club = playerItem.getClub();
        String name = playerItem.getName();
        String category = playerItem.getCategory();

        tvNo.setText(club);
        tvNama.setText(name);
        tvCategory.setText(category);


    }
}
