package com.example.climaapp.main;

import com.example.climaapp.DailyWeatherItem;
import com.example.climaapp.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class MainModel {

    public Observable<List<DailyWeatherItem>> getWeatherData() {
        //cria o observable
        return Observable.create(new ObservableOnSubscribe<List<DailyWeatherItem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DailyWeatherItem>> emitter) throws Exception {
                // data to populate the RecyclerView with
                List<DailyWeatherItem> myListData = new ArrayList();
                myListData.add(new DailyWeatherItem("Segunda", R.drawable.cloudy, "30º", "18º"));
                myListData.add(new DailyWeatherItem("Terça", R.drawable.sun, "27º", "15º"));
                myListData.add(new DailyWeatherItem("Quarta", R.drawable.rays, "20º", "13º"));
                myListData.add(new DailyWeatherItem("Quinta", R.drawable.moon, "23º", "11º"));
                myListData.add(new DailyWeatherItem("Sexta", R.drawable.rain_night, "27º", "20º"));
                myListData.add(new DailyWeatherItem("Sábado", R.drawable.mist, "18º", "11º"));
                myListData.add(new DailyWeatherItem("Domingo", R.drawable.snow, "19º", "9º"));

                //Envia a lista para o presenter
                emitter.onNext(myListData);

                //Termina o observable
                emitter.onComplete();
            }
        });
    }

}
