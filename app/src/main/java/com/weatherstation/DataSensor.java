package com.weatherstation;

/**
 * Created by RadenMas on 19/05/2022.
 */
public class DataSensor {
    int dir, hum, temp, wind;
    long time;

    public DataSensor() {
    }

    public DataSensor(int dir, int hum, int temp, int wind, long time) {
        this.dir = dir;
        this.hum = hum;
        this.temp = temp;
        this.wind = wind;
        this.time = time;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getWind() {
        return wind;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
