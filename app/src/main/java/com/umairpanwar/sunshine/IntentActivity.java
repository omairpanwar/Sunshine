package com.umairpanwar.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umairpanwar.sunshine.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class IntentActivity extends AppCompatActivity {

    private TextView day;
    private TextView date;
    private TextView maxtepm;
    private TextView mintemp;
    private TextView humidity;
    private TextView pressure;
    private TextView wimd;
    private TextView location;
    private ImageView imageview;
    private TextView clearsky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity);
        day = findViewById(R.id.daytextview);
        date = findViewById(R.id.datetextview);
        maxtepm = findViewById(R.id.maxtemp);
        mintemp = findViewById(R.id.mintemp);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        wimd = findViewById(R.id.wind);
        location = findViewById(R.id.location);
        imageview = findViewById(R.id.imageview);
        clearsky = findViewById(R.id.skyclear);


        Bundle intent = getIntent().getExtras();
        DataModel dataModel = (DataModel) intent.getSerializable("ff");


        day.setText(dataModel.getDayofweek());
        SimpleDateFormat df = new SimpleDateFormat("MMM d",Locale.UK);
        String time = df.format(dataModel.getDate()*1000);
        date.setText(time);
        maxtepm.setText(String.valueOf(dataModel.getMaxtemp()+ "\u00B0"));
        mintemp.setText(String.valueOf(dataModel.getMintemp()+ "\u00B0"));
        humidity.setText(String.valueOf(dataModel.getHumidity()+ " %"));
        pressure.setText(String.valueOf(dataModel.getPressure()+ " hPa"));
        wimd.setText(String.valueOf(dataModel.getWind()+ " km/h NE"));
        location.setText(dataModel.getLocation());
        clearsky.setText(dataModel.getStatus());


        if (dataModel.getStatus().toLowerCase().contains("rain")){
            imageview.setImageResource(R.drawable.rain);
        }else if (dataModel.getStatus().toLowerCase().contains("clouds")){
            imageview.setImageResource(R.drawable.clouds);
        }else if (dataModel.getStatus().toLowerCase().contains("scattered clouds")){
          imageview.setImageResource(R.drawable.scatertd);
        }else
            imageview.setImageResource(R.drawable.sunny);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    }

