package com.weatherstation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weatherstation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        FirebaseDatabase.getInstance().getReference("realtime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataRealtime data = snapshot.getValue(DataRealtime.class);
                String cardinalDirection = null;
                if ((data.arahAngin >= 348.75) && (data.arahAngin <= 360) ||
                        (data.arahAngin >= 0) && (data.arahAngin <= 11.25)) {
                    cardinalDirection = "N";
                } else if ((data.arahAngin >= 11.25) && (data.arahAngin <= 33.75)) {
                    cardinalDirection = "NNE";
                } else if ((data.arahAngin >= 33.75) && (data.arahAngin <= 56.25)) {
                    cardinalDirection = "NE";
                } else if ((data.arahAngin >= 56.25) && (data.arahAngin <= 78.75)) {
                    cardinalDirection = "ENE";
                } else if ((data.arahAngin >= 78.75) && (data.arahAngin <= 101.25)) {
                    cardinalDirection = "E";
                } else if ((data.arahAngin >= 101.25) && (data.arahAngin <= 123.75)) {
                    cardinalDirection = "ESE";
                } else if ((data.arahAngin >= 123.75) && (data.arahAngin <= 146.25)) {
                    cardinalDirection = "SE";
                } else if ((data.arahAngin >= 146.25) && (data.arahAngin <= 168.75)) {
                    cardinalDirection = "SSE";
                } else if ((data.arahAngin >= 168.75) && (data.arahAngin <= 191.25)) {
                    cardinalDirection = "S";
                } else if ((data.arahAngin >= 191.25) && (data.arahAngin <= 213.75)) {
                    cardinalDirection = "SSW";
                } else if ((data.arahAngin >= 213.75) && (data.arahAngin <= 236.25)) {
                    cardinalDirection = "SW";
                } else if ((data.arahAngin >= 236.25) && (data.arahAngin <= 258.75)) {
                    cardinalDirection = "WSW";
                } else if ((data.arahAngin >= 258.75) && (data.arahAngin <= 281.25)) {
                    cardinalDirection = "W";
                } else if ((data.arahAngin >= 281.25) && (data.arahAngin <= 303.75)) {
                    cardinalDirection = "WNW";
                } else if ((data.arahAngin >= 303.75) && (data.arahAngin <= 326.25)) {
                    cardinalDirection = "NW";
                } else if ((data.arahAngin >= 326.25) && (data.arahAngin <= 348.75)) {
                    cardinalDirection = "NNW";
                }

                binding.Darah.setText(data.arahAngin + " " + cardinalDirection);
                binding.Dsuhu.setText(data.temperature + "\u2103");
                binding.Dkelembapan.setText(data.kelembapan + "\u0025");
                binding.Dkecepatan.setText(data.kecepatanAngin + "m/s");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.cardSuhu.setOnClickListener(view1 -> startActivity(new Intent(this, SuhuActivity.class)));
        binding.cardKelembapan.setOnClickListener(view1 -> startActivity(new Intent(this, KelembapanActivity.class)));
        binding.cardKecepatan.setOnClickListener(view1 -> startActivity(new Intent(this, KecepatanAnginActivity.class)));
        binding.cardArah.setOnClickListener(view1 -> startActivity(new Intent(this, ArahAnginActivity.class)));

    }

    public void ClickMenu(View view) {
        //open drawer
        openDrawer(binding.drawerlayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        //close drawer
        closeDrawer(binding.drawerlayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickSuhu(View view) {
        redirectActivity(this, SuhuActivity.class);
    }

    public void ClickKelembapan(View view) {
        redirectActivity(this, KelembapanActivity.class);
    }

    public void ClickKecepatanAngin(View view) {
        redirectActivity(this, KecepatanAnginActivity.class);
    }

    public void ClickArahAngin(View view) {
        redirectActivity(this, ArahAnginActivity.class);
    }

    public void ClickTentang(View view) {
        redirectActivity(this, TentangActivity.class);
    }

    public void ClickLogout(View view) {
        logout(this);
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("apakah anda yakin ingin keluar ?");
        builder.setPositiveButton("Ya", (dialogInterface, which) -> {
            activity.finishAffinity();
            System.exit(0);
        });

        builder.setNegativeButton("tidak", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(binding.drawerlayout);
    }
}
