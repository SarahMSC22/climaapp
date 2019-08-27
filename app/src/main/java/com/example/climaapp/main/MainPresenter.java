package com.example.climaapp.main;

import android.util.Log;

import com.example.climaapp.DailyWeatherItem;
import com.example.climaapp.R;
import com.example.climaapp.RetrofitConfig;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends MvpNullObjectBasePresenter<MainView> {
    //Responsável pelos dados

    private final MainModel mModel;

    public MainPresenter(MainModel model) {
        mModel = model;
    }

    public void getWeatherData() {
        mModel.getWeatherData() //Chama o método do model para buscar a lista
                .subscribeOn(Schedulers.io()) //Cria uma nova thread para executar em background
                .observeOn(AndroidSchedulers.mainThread()) //Indica para qual thread deve ser enviada a resposta
                .subscribe(new Consumer<List<DailyWeatherItem>>() {  //inicia o observable
                    @Override
                    public void accept(List<DailyWeatherItem> itens) throws Exception {  //retorna resposta de sucesso
                        //Manda os itens para a activity
                        getView().showWeatherList(itens);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception { // retorna resposta de erro
                        Log.d("Sarah", throwable.getMessage());
                    }
                });


    }

}
