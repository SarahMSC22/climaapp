package com.example.climaapp.main;

import com.example.climaapp.DailyWeatherItem;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface MainView extends MvpView {

    public void showWeatherList(List<DailyWeatherItem> myListData);
}
