package com.umairpanwar.sunshine;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.umairpanwar.sunshine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class StartActivity extends AppCompatActivity {

    public Context context;
    public RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_recyclerview_widget);
        this.context = this;

        recyclerView = findViewById(R.id.mainRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new JsonApiWeather("hafizabad").execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.add("Item3").setActionView(R.layout.first_view_holder).setCheckable(true);
        return super.onCreateOptionsMenu(menu);
        //  return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menulocation:
                Intent intent = new Intent(this, CitiesListRecyclerview.class);
                startActivityForResult(intent, 1);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String str = data.getStringExtra("hh");
                new JsonApiWeather(str).execute();
            }
        }
    }

    public class JsonApiWeather extends AsyncTask<Void, Void, String> {
        DataModel[] cities;

        private final String city;

        public JsonApiWeather(String city) {
            this.city = city;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (cities != null) {

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, cities);
                recyclerView.setAdapter(adapter);
            } else
                Toast.makeText(StartActivity.this, "No data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String json = getfromUrl("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&APPID=6deef80072e76064fb9d84408ab92987&units=metric&cnt=7");
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray list = jsonObject.getJSONArray("list");
                    cities = new DataModel[list.length()];
                    for (int i = 0; i < list.length(); i++) {
                        DataModel dataModel = new DataModel();
                        JSONObject object = list.getJSONObject(i);
                        JSONObject main = object.getJSONObject("main");
                        JSONArray weatherArray = object.getJSONArray("weather");
                        if (weatherArray.length() > 0) {
                            JSONObject weatherobject = weatherArray.getJSONObject(0);
                            dataModel.setStatus(weatherobject.getString("description"));
                        }
                        dataModel.setDate(object.getLong("dt"));
                        dataModel.setHumidity(main.getDouble("humidity"));
                        dataModel.setPressure(main.getDouble("pressure"));
                        JSONObject windobj = object.getJSONObject("wind");
                        dataModel.setWind(windobj.getDouble("speed"));
                        JSONObject cityObject = jsonObject.getJSONObject("city");
                        dataModel.setLocation(cityObject.getString("name"));
                        dataModel.setMaxtemp(main.getInt("temp_max"));
                        dataModel.setMintemp(main.getInt("temp_min"));
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sd = new SimpleDateFormat("EEEE");
                        String dayofweek;
                        //hello

                        if (i == 0) {
                            dayofweek = sd.format(calendar.getTime());
                        } else {
                            dayofweek = sd.format(new Date().getTime() + i * TimeUnit.DAYS.toMillis(1));
                        }
                        dataModel.setDayofweek(dayofweek);
                        cities[i] = dataModel;

                        Log.d("jhd", "shd" + json);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.getStackTrace();
                e.printStackTrace();
            }


            return null;
        }
    }

    public String getfromUrl(String murl) throws IOException {
        URL url = new URL(murl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        line = stringBuilder.toString();
        connection.disconnect();
        inputStream.close();
        stringBuilder.delete(0, stringBuilder.length());
        return line;
    }

}
