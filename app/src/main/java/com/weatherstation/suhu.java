package com.weatherstation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class suhu extends AppCompatActivity {

    private ImageView btn_bck1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suhu);

        btn_bck1 = findViewById(R.id.btn_bck1);

        btn_bck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(suhu.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}