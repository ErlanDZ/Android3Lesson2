package com.example.android3lesson2.utils;

import android.app.Application;

import com.example.android3lesson2.data.network.RetrofitClient;
import com.example.android3lesson2.data.network.apiservices.CharacterApiService;
import com.example.android3lesson2.data.network.apiservices.EpisodeApiService;
import com.example.android3lesson2.data.network.apiservices.LocationApiService;

public class App extends Application {
    public static CharacterApiService characterApiService;
    public static EpisodeApiService episodeApiService ;
    public static LocationApiService locationApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient retrofitClient = new RetrofitClient();
        characterApiService = retrofitClient.provideCharacterApiService();
        locationApiService = retrofitClient.provideLocationApiService();
        episodeApiService = retrofitClient.provideEpisodeApiService();


    }
}
