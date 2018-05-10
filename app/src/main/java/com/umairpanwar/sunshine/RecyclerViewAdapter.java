package com.umairpanwar.sunshine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umairpanwar.sunshine.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BaseHolder>{

        private final Context context;
        DataModel[]dataModels;

        public RecyclerViewAdapter(Context context, DataModel[]dataModels) {
            this.context = context;
            this.dataModels=dataModels;

        }


    @NonNull
        @Override
        public RecyclerViewAdapter.BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType==0){
                return new FirstViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.first_view_holder,parent,false));

            }else {
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.second_view_holder, parent, false));
            }
        }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
        public void onBindViewHolder(@NonNull RecyclerViewAdapter.BaseHolder holder, int position) {

            if (holder instanceof  FirstViewHolder){

                DataModel item2 = dataModels[position];
                FirstViewHolder holder2 = ((FirstViewHolder) holder);
                holder2.fviewmaxtemp.setText(String.valueOf(item2.getMaxtemp())+"\u00B0");
                holder2.fviewmintemp.setText(String.valueOf(item2.getMintemp())+"\u00B0");
                holder2.fviewstatusblue.setText(String.valueOf(item2.getStatus()));
                holder2.fviewcntry.setText(String.valueOf(item2.getLocation()));
                holder2.fviewdate.setText("Today");

                if (item2.getStatus().toLowerCase().contains("light rain")){
                    holder2.fviewbigimg.setImageResource(R.drawable.rain);
                    holder2.fviewsmallimg.setImageResource(R.drawable.rain);
                }else if (item2.getStatus().toLowerCase().contains("clouds")){
                    holder2.fviewbigimg.setImageResource(R.drawable.clouds);
                    holder2.fviewsmallimg.setImageResource(R.drawable.clouds);
                }else if (item2.getStatus().toLowerCase().contains("scattered clouds")){
                    holder2.fviewbigimg.setImageResource(R.drawable.scatertd);
                    holder2.fviewsmallimg.setImageResource(R.drawable.scatertd);
                }else {
                    holder2.fviewbigimg.setImageResource(R.drawable.sunny);
                    holder2.fviewsmallimg.setImageResource(R.drawable.sunny);
                }


            }else if(holder instanceof ViewHolder) {
                DataModel item = dataModels[position];
                ViewHolder holder1= ((ViewHolder) holder);
                if (position==1){
                    holder1.textView1.setText("Tomorrow");
                }else {
                holder1.textView1.setText(String.valueOf(item.getDayofweek()));}
                holder1.textView2.setText(String.valueOf(item.getStatus()));
                holder1.textView3.setText(String.valueOf(item.getMaxtemp())+"\u00B0");
                holder1.textView4.setText(String.valueOf(item.getMintemp())+"\u00B0");
                if (item.getStatus().toLowerCase().contains("light rain")){
                    holder1.imageView.setImageResource(R.drawable.rain);
                }else if (item.getStatus().toLowerCase().contains("clouds")){
                    holder1.imageView.setImageResource(R.drawable.clouds);

                }else if (item.getStatus().toLowerCase().contains("scattered clouds")){
                    holder1.imageView.setImageResource(R.drawable.scatertd);

                }else {
                    holder1.imageView.setImageResource(R.drawable.sunny);

                }
            }

        }

        @Override
        public int getItemCount() {
            return dataModels.length;
        }
        public class ViewHolder extends BaseHolder{

            ImageView imageView;
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;


            public ViewHolder(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.recyclerImageView);
                textView1 = itemView.findViewById(R.id.mainrecTextViewday);
                textView2 = itemView.findViewById(R.id.mainTextViewstatus);
                textView3 = itemView.findViewById(R.id.mainTextViewmaxtemp);
                textView4 = itemView.findViewById(R.id.mainTextViewmintemp);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        DataModel data = dataModels[position];
                        Intent intent = new Intent(context,IntentActivity.class);
                        intent.putExtra("ff",data);
                        context.startActivity(intent);
                    }
                });
            }
        }

        class FirstViewHolder extends BaseHolder{

            ImageView fviewbigimg;
            ImageView fviewsmallimg;
            TextView fviewstatusblue;
            TextView fviewcntry;
            TextView fviewmaxtemp;
            TextView fviewmintemp;
            TextView fviewdate;

            public FirstViewHolder(View itemView) {
                super(itemView);

                fviewbigimg = itemView.findViewById(R.id.mainImageViewbigblue);
                fviewsmallimg = itemView.findViewById(R.id.mainImageViewsmallsun);
                fviewstatusblue = itemView.findViewById(R.id.mainTextViewstatusblue);
                fviewcntry = itemView.findViewById(R.id.mainTextViewcitycntry);
                fviewmaxtemp = itemView.findViewById(R.id.mainTextViewmaxtmpblue);
                fviewmintemp = itemView.findViewById(R.id.mainTextViewmintempblue);
                fviewdate = itemView.findViewById(R.id.mainTextViewdate);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        DataModel data = dataModels[position];
                        Intent intent = new Intent(context,IntentActivity.class);
                        intent.putExtra("ff",data);
                        context.startActivity(intent);
                    }
                });
            }
        }

        class BaseHolder extends RecyclerView.ViewHolder{

            public BaseHolder(View itemView) {
                super(itemView);
            }
        }
    }