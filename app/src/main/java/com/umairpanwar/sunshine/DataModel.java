package com.umairpanwar.sunshine;


import java.io.Serializable;

public class DataModel implements Serializable {

    private int image;
    private  int maxtemp;
    private int mintemp;
    private String status;
    private long date;
    private String location;
    private String dayofweek;
    private double humidity;
    private  double wind;
    private double pressure;

    public DataModel(){}

    public DataModel(int image, int maxtemp, int mintemp, String status, long date, String location, String dayofweek, double humidity, double wind, double pressure) {
        this.image = image;
        this.maxtemp = maxtemp;
        this.mintemp = mintemp;
        this.status = status;
        this.date = date;
        this.location = location;
        this.dayofweek = dayofweek;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(int maxtemp) {
        this.maxtemp = maxtemp;
    }

    public int getMintemp() {
        return mintemp;
    }

    public void setMintemp(int mintemp) {
        this.mintemp = mintemp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }
}
