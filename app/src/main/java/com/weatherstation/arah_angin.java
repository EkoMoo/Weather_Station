package com.weatherstation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class arah_angin extends AppCompatActivity {

    private ImageView btn_bck4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arah_angin);

        btn_bck4 = findViewById(R.id.btn_bck4);

        btn_bck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(arah_angin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}