package com.weatherstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.weatherstation.databinding.ActivitySuhuBinding;
import com.weatherstation.databinding.ActivityTentangBinding;

public class TentangActivity extends AppCompatActivity {
    private ImageView btn_bck5;

    private ActivityTentangBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTentangBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBck5.setOnClickListener(view1 -> onBackPressed());
    }
}