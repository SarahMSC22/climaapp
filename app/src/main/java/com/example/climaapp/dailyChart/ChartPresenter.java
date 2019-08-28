package com.example.climaapp.dailyChart;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

public class ChartPresenter extends MvpNullObjectBasePresenter<ChartView> {

    private final ChartModel mModelChart;

    public ChartPresenter(ChartModel modelChart) {
        mModelChart = modelChart;
    }
}
