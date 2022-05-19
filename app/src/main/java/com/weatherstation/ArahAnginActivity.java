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
import com.weatherstation.databinding.ActivityArahAnginBinding;
import com.weatherstation.databinding.ActivityKecepatanAnginBinding;

import java.util.ArrayList;

public class ArahAnginActivity extends AppCompatActivity {

    private ActivityArahAnginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArahAnginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBck4.setOnClickListener(view1 -> onBackPressed());

        FirebaseDatabase.getInstance().getReference("monitoring").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> data = new ArrayList<>();
                if (snapshot.hasChildren()) {
                    for (DataSnapshot child : snapshot.getChildren()) {

                        DataSensor dataSensor = child.getValue(DataSensor.class);
                        data.add(new Entry(dataSensor.getTime(), dataSensor.getDir()));
                    }

                    ShowChart chart = new ShowChart();
                    chart.chart(binding.chartWindD, data, 0, 360);
                }
                binding.chartWindD.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}