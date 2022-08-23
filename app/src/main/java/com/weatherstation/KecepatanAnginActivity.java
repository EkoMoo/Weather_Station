package com.weatherstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weatherstation.databinding.ActivityKecepatanAnginBinding;
import com.weatherstation.databinding.ActivityKelembapanBinding;

import java.util.ArrayList;

public class KecepatanAnginActivity extends AppCompatActivity {

    private ActivityKecepatanAnginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKecepatanAnginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBck3.setOnClickListener(view1 -> onBackPressed());

        FirebaseDatabase.getInstance().getReference("monitoring").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> data = new ArrayList<>();
                if (snapshot.hasChildren()) {
                    for (DataSnapshot child : snapshot.getChildren()) {

                        DataSensor dataSensor = child.getValue(DataSensor.class);
                        data.add(new Entry(dataSensor.getTime(), dataSensor.getWind()));
                    }

                    ShowChart chart = new ShowChart();
                    chart.chart(binding.chartWindS, data, 0, 35);
                }
                binding.chartWindS.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}