package com.weatherstation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private CardView btn_suhu, btn_kelembapan, btn_kecepatan, btn_arah;
    private TextView arah, temp, hum, wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);

        btn_suhu = findViewById(R.id.btn_suhu);
        btn_kelembapan = findViewById(R.id.btn_kelembapan);
        btn_kecepatan = findViewById(R.id.btn_kecepatan);
        btn_arah = findViewById(R.id.btn_arah);

        arah = findViewById(R.id.Darah);
        temp = findViewById(R.id.Dsuhu);
        hum = findViewById(R.id.Dkelembapan);
        wind = findViewById(R.id.Dkecepatan);


        FirebaseDatabase.getInstance().getReference("realtime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataRealtime data = snapshot.getValue(DataRealtime.class);
                arah.setText("" + data.arahAngin);
                temp.setText("" + data.temperature);
                hum.setText("" + data.kelembapan);
                wind.setText("" + data.kecepatanAngin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_suhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SuhuActivity.class));
            }
        });

        btn_kelembapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KelembapanActivity.class));
            }
        });

        btn_kecepatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KecepatanAnginActivity.class));
            }
        });

        btn_arah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ArahAnginActivity.class));
            }
        });

    }

    public void ClickMenu(View view) {
        //open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        //close drawer
        closeDrawer(drawerLayout);

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
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

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
        closeDrawer(drawerLayout);
    }
}
