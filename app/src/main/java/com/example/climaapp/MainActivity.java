package com.example.climaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        //        getWeather();


        // data to populate the RecyclerView with
        List<DailyWeatherItem> myListData = new ArrayList();
        myListData.add(new DailyWeatherItem("Segunda", R.drawable.cloudy, "30º", "18º"));
        myListData.add(new DailyWeatherItem("Terça", R.drawable.sun, "27º", "15º"));
        myListData.add(new DailyWeatherItem("Quarta", R.drawable.rays, "20º", "13º"));
        myListData.add(new DailyWeatherItem("Quinta", R.drawable.heavy_rain, "23º", "11º"));
        myListData.add(new DailyWeatherItem("Sexta", R.drawable.light_rain, "27º", "20º"));
        myListData.add(new DailyWeatherItem("Sábado", R.drawable.mist, "18º", "11º"));
        myListData.add(new DailyWeatherItem("Domingo", R.drawable.snow, "19º", "9º"));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        adapter = new MyRecyclerViewAdapter(this, myListData);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                horizontalLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " +(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }


    private void getWeather() {
        new RetrofitConfig().getAPIService()
                .getWeather("Salvador,BR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("CHVG", o.toString());
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("CHVG", throwable.getMessage());

                    }
                });


    }


}
