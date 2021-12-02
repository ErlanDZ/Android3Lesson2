package com.example.android3lesson2.data.local;

import android.content.Context;

import androidx.room.Room;

import com.example.android3lesson2.data.local.daos.CharacterDao;

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
}
