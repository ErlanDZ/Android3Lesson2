package com.example.android3lesson2.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android3lesson2.data.local.daos.CharacterDao;
import com.example.android3lesson2.data.local.daos.EpisodeDao;
import com.example.android3lesson2.data.local.daos.LocationDao;
import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;

@Database(entities = {Character.class, LocationModel.class, EpisodeModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract CharacterDao characterDao();

    public abstract LocationDao locationDao();

    public abstract EpisodeDao episodeDao();
}
