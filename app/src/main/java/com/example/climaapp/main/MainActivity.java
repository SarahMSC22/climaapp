package com.example.climaapp.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.climaapp.DailyWeatherItem;
import com.example.climaapp.MyRecyclerViewAdapter;
import com.example.climaapp.R;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MyRecyclerViewAdapter.ItemClickedListener, MainView {

    //Responsável pelo layout

    MyRecyclerViewAdapter mAdapter;
    List<DailyWeatherItem> myListData = new ArrayList<>();

    TextView mMaximum;
    TextView mMinimum;

    @BindView(R.id.day)
    TextView mDayOfWeek;

    @BindView(R.id.weather_image)
    ImageView mWeatherOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        //TODO utilizar butterKnife
        mMaximum = (TextView) findViewById(R.id.maximum);
        mMinimum = (TextView) findViewById(R.id.minimum);

        initRecyclerview();
        getPresenter().getWeatherData();

        //TODO aprender como selecionar o primeiro item do recyclerview e mostrar ele quando o app abrir
    }

    private void initRecyclerview() {
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main_recycler);
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
    public void showWeatherList(List<DailyWeatherItem> listData) {
        this.myListData.addAll(listData);
        mAdapter.notifyDataSetChanged();
    }
}
