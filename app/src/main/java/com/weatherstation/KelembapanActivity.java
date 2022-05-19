package com.weatherstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.data.Entry;
import com.weatherstation.databinding.ActivityKelembapanBinding;
import com.weatherstation.databinding.ActivitySuhuBinding;

import java.util.ArrayList;

public class KelembapanActivity extends AppCompatActivity {

    private ActivityKelembapanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKelembapanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBck2.setOnClickListener(view1 -> onBackPressed());

        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1F, 5F));
        values.add(new Entry(2F, 6F));
        values.add(new Entry(3F, 3F));
        values.add(new Entry(4F, 6F));
        values.add(new Entry(5F, 9F));
        values.add(new Entry(6F, 10F));
        values.add(new Entry(7F, 5F));
        values.add(new Entry(8F, 7F));
        values.add(new Entry(9F, 8F));
        values.add(new Entry(10F, 9F));
        values.add(new Entry(11F, 5F));
        values.add(new Entry(12F, 3F));

        ShowChart chart = new ShowChart();
        chart.chart(binding.chartHum, values, 0, 15);

    }
}