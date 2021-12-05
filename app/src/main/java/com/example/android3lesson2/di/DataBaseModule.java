package com.example.android3lesson2.di;


import android.content.Context;

import com.example.android3lesson2.data.local.AppDataBase;
import com.example.android3lesson2.data.local.RoomClient;
import com.example.android3lesson2.data.local.daos.CharacterDao;
import com.example.android3lesson2.data.local.daos.EpisodeDao;
import com.example.android3lesson2.data.local.daos.LocationDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {

    @Singleton
    RoomClient roomClient = new RoomClient();

    @Provides
    @Singleton
    AppDataBase provideAppDataBase(@ApplicationContext Context context) {
        return roomClient.provideDataBase(context);
    }


    @Singleton
    @Provides
    public CharacterDao provideCharacterDao(AppDataBase appDataBase) {
        return roomClient.provideCharacterDao(appDataBase);
    }

    @Singleton
    @Provides
    public EpisodeDao provideEpisodeDao (AppDataBase dataBase){
        return roomClient.provideEpisodeDao(dataBase);
    }

    @Singleton
    @Provides
    public LocationDao provideLocationDao (AppDataBase dataBase){
        return roomClient.provideLocationDao(dataBase);
    }
}
