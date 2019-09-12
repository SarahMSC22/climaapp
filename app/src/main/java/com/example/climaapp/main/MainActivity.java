package com.example.climaapp.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.climaapp.DailyWeatherItem;
import com.example.climaapp.MyRecyclerViewAdapter;
import com.example.climaapp.R;
import com.example.climaapp.dailyChart.ChartActivity;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MyRecyclerViewAdapter.ItemClickedListener, MainView {

    //Responsável pelo layout

    MyRecyclerViewAdapter mAdapter;
    List<DailyWeatherItem> myListData = new ArrayList<>();

    @BindView(R.id.day)
    TextView mDayOfWeek;

    @BindView(R.id.weather_image)
    ImageView mWeatherOfDay;

    @BindView(R.id.maximum)
    TextView mMaximum;

    @BindView(R.id.minimum)
    TextView mMinimum;

    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ButterKnife.bind(MainActivity.this);

        initRecyclerview();
        getPresenter().getWeatherData();
    }

    private void initRecyclerview() {
        // set up the RecyclerView
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(this, myListData);
        mAdapter.setClickListener(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                horizontalLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);
    }

    //Cria o presenter para executar as regras de negocio
    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(new MainModel());
    }

    @Override
    public void onItemClicked(DailyWeatherItem item) {
        mDayOfWeek.setText(item.getDescription());
        mWeatherOfDay.setImageResource(item.getImgId());
        mMinimum.setText(item.getMin());
        mMaximum.setText(item.getMax());
    }

    @Override
    public void onLongClick(DailyWeatherItem item) {
        Intent intent = new Intent(MainActivity.this, ChartActivity.class);
        startActivity(intent);

    }

    @Override
    public void showWeatherList(List<DailyWeatherItem> listData) {
        this.myListData.addAll(listData);
        mAdapter.notifyDataSetChanged();
        onItemClicked(listData.get(0));
    }
}
