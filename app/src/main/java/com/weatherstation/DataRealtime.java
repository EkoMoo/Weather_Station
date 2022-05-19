package com.weatherstation;

/**
 * Created by RadenMas on 19/05/2022.
 */
public class DataRealtime {
    int arahAngin, kecepatanAngin;
    float kelembapan, temperature;

    public DataRealtime() {
    }

    public DataRealtime(int arahAngin, int kecepatanAngin, float kelembapan, float temperature) {
        this.arahAngin = arahAngin;
        this.kecepatanAngin = kecepatanAngin;
        this.kelembapan = kelembapan;
        this.temperature = temperature;
    }

    public int getArahAngin() {
        return arahAngin;
    }

    public void setArahAngin(int arahAngin) {
        this.arahAngin = arahAngin;
    }

    public int getKecepatanAngin() {
        return kecepatanAngin;
    }

    public void setKecepatanAngin(int kecepatanAngin) {
        this.kecepatanAngin = kecepatanAngin;
    }

    public float getKelembapan() {
        return kelembapan;
    }

    public void setKelembapan(float kelembapan) {
        this.kelembapan = kelembapan;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
