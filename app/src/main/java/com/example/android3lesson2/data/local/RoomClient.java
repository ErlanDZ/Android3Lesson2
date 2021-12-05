package com.example.android3lesson2.data.local;

import android.content.Context;

import androidx.room.Room;

import com.example.android3lesson2.data.local.daos.CharacterDao;
import com.example.android3lesson2.data.local.daos.EpisodeDao;
import com.example.android3lesson2.data.local.daos.LocationDao;

public class RoomClient {
    public AppDataBase provideDataBase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "rick_morty_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public CharacterDao provideCharacterDao(AppDataBase dataBase) {
        return dataBase.characterDao();
    }

    public EpisodeDao provideEpisodeDao(AppDataBase dataBase){
        return  dataBase.episodeDao();
    }

    public LocationDao provideLocationDao(AppDataBase dataBase){
        return dataBase.locationDao();
    }
}
