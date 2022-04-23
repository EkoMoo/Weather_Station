package com.weatherstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class kelembapan extends AppCompatActivity {
    private ImageView btn_bck2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelembapan);

        btn_bck2 = findViewById(R.id.btn_bck2);

        btn_bck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kelembapan.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}