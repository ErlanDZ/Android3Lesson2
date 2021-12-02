package com.example.android3lesson2.utils;

import android.app.Application;

import com.example.android3lesson2.data.local.AppDataBase;
import com.example.android3lesson2.data.local.RoomClient;
import com.example.android3lesson2.data.local.daos.CharacterDao;
import com.example.android3lesson2.data.local.daos.EpisodeDao;
import com.example.android3lesson2.data.local.daos.LocationDao;
import com.example.android3lesson2.data.network.RetrofitClient;
import com.example.android3lesson2.data.network.apiservices.CharacterApiService;
import com.example.android3lesson2.data.network.apiservices.EpisodeApiService;
import com.example.android3lesson2.data.network.apiservices.LocationApiService;

public class App extends Application {
    public static CharacterApiService characterApiService;
    public static EpisodeApiService episodeApiService;
    public static LocationApiService locationApiService;

    public static CharacterDao characterDao;
    public static LocationDao locationDao;
    public static EpisodeDao episodeDao;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient retrofitClient = new RetrofitClient();
        RoomClient roomClient = new RoomClient();
        AppDataBase dataBase = roomClient.provideDataBase(this);

        characterApiService = retrofitClient.provideCharacterApiService();
        locationApiService = retrofitClient.provideLocationApiService();
        episodeApiService = retrofitClient.provideEpisodeApiService();

        characterDao = dataBase.characterDao();
        locationDao = dataBase.locationDao();
        episodeDao = dataBase.episodeDao();


    }
}
