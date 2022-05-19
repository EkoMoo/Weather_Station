package com.weatherstation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weatherstation.databinding.ActivitySuhuBinding;

import java.util.ArrayList;

public class SuhuActivity extends AppCompatActivity {

    private ActivitySuhuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuhuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBck1.setOnClickListener(view1 -> {
            onBackPressed();
        });

        FirebaseDatabase.getInstance().getReference("monitoring").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> data = new ArrayList<>();
                if (snapshot.hasChildren()) {
                    for (DataSnapshot child : snapshot.getChildren()) {

                        DataSensor dataSensor = child.getValue(DataSensor.class);
                        data.add(new Entry(dataSensor.getTime(), dataSensor.getTemp()));
                    }

                    ShowChart chart = new ShowChart();
                    chart.chart(binding.chartTemp, data, 0, 120);
                }
                binding.chartTemp.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}