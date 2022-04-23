package com.weatherstation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class kecepatan_angin extends AppCompatActivity {

    private ImageView btn_bck3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kecepatan_angin);

        btn_bck3 = findViewById(R.id.btn_bck3);

        btn_bck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kecepatan_angin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}