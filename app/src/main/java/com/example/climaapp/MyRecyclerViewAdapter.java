package com.example.climaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<DailyWeatherItem> mWeatherList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<DailyWeatherItem> myListData) {
        this.mInflater = LayoutInflater.from(context);
        this.mWeatherList = myListData;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myView.setImageResource(mWeatherList.get(position).getImgId());
        holder.myTextView.setText(mWeatherList.get(position).getDescription());
        holder.myTextMax.setText(mWeatherList.get(position).getMax());
        holder.myTextMin.setText(mWeatherList.get(position).getMin());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView myTextView;
        TextView myTextMax;
        TextView myTextMin;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.image_weather);
            myTextView = itemView.findViewById(R.id.date_text);
            myTextMax = itemView.findViewById(R.id.max_temperature);
            myTextMin = itemView.findViewById(R.id.min_temperature);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
//    public String getItem(int id) {
//        return mWeatherList.get(id);
//    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}