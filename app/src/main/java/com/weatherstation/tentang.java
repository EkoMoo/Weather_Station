package com.weatherstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class tentang extends AppCompatActivity {
    private ImageView btn_bck5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        btn_bck5 = findViewById(R.id.btn_bck5);

        btn_bck5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tentang.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}