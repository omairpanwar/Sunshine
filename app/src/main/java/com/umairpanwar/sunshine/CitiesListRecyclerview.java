package com.umairpanwar.sunshine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umairpanwar.sunshine.R;

public class CitiesListRecyclerview extends AppCompatActivity {


    public Context context1;
    public RecyclerView recyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_list_recyclerview_widget);

        this.context1=this;
        recyclerView1 = findViewById(R.id.mainRecyclerview);
        recyclerView1.setLayoutManager(new LinearLayoutManager(context1));
        OurAdapter1 ourAdapter1 = new OurAdapter1();
        recyclerView1.setAdapter(ourAdapter1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private class OurAdapter1 extends RecyclerView.Adapter<OurAdapter1.ViewHolder>{
        DataModel[] dataModels = new DataModel[10];
        public OurAdapter1() {
            dataModels[0] = new DataModel("Hafizabad");
            dataModels[1] = new DataModel("Gujranwala");
            dataModels[2] = new DataModel("Lahore");
            dataModels[3] = new DataModel("Karachi");
            dataModels[4] = new DataModel("Islamabad");
            dataModels[5] = new DataModel("Peshawar");
            dataModels[6] = new DataModel("Multan");
            dataModels[7] = new DataModel("Faisalabad");
            dataModels[8] = new DataModel("New York");
            dataModels[9] = new DataModel("Landon");

        }

        @NonNull
        @Override
        public OurAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context1).inflate(R.layout.recyclerview_cities_items, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull OurAdapter1.ViewHolder holder, int position) {
          DataModel item = dataModels[position];
          holder.textView.setText(item.getCities());
        }

        @Override
        public int getItemCount() {
            return dataModels.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        DataModel data = dataModels[position];
                        Intent intent = new Intent();
                        intent.putExtra("hh",data.getCities());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                textView = itemView.findViewById(R.id.cities);
            }
        }
    }
    public class DataModel{
        private String cities;

        public DataModel(String cities) {
            this.cities = cities;
        }



        public String getCities() {
            return cities;
        }

        public void setCities(String cities) {
            this.cities = cities;
        }
    }
}
