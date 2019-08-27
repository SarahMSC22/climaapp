package com.example.climaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<DailyWeatherItem> mWeatherList;
    private LayoutInflater mInflater;
    private ItemClickedListener mClickListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<DailyWeatherItem> myListData) {
        this.mContext = context;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //Biblioteca para carregar imagens
        Glide.with(mContext)
                .load(mWeatherList.get(position).getImgId())
                .into(holder.myView);

        holder.myTextView.setText(mWeatherList.get(position).getDescription());
        holder.myTextMax.setText(mWeatherList.get(position).getMax());
        holder.myTextMin.setText(mWeatherList.get(position).getMin());
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClicked(mWeatherList.get(position));

            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        TextView myTextMax;
        TextView myTextMin;
        View mContainer;

        @BindView(R.id.weather)
        ImageView myView;

        ViewHolder(View itemView) {
            super(itemView);

            //TODO utilizar butterKnife
            myTextView = itemView.findViewById(R.id.date_text);
            myTextMax = itemView.findViewById(R.id.max_temperature);
            myTextMin = itemView.findViewById(R.id.min_temperature);
            mContainer = itemView.findViewById(R.id.container);

        }

    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickedListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickedListener {
        void onItemClicked(DailyWeatherItem item);
    }
}