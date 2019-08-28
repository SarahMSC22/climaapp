package com.example.climaapp.dailyChart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.climaapp.DailyWeatherItem;
import com.example.climaapp.MyRecyclerViewAdapter;
import com.example.climaapp.R;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

public class ChartActivity extends MvpActivity<ChartView, ChartPresenter>
        implements ChartView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

    }

    @NonNull
    @Override
    public ChartPresenter createPresenter() {
        return new ChartPresenter(new ChartModel());
    }

}

