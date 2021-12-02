package com.example.android3lesson2.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<LocationModel> locationModels);

    @Query("SELECT * FROM episodemodel LIMIT 1")
    List<EpisodeModel> getAnyRecipe();


    @Delete
    void delete(LocationModel locationModel);

    @Query("SELECT * FROM locationmodel")
    List<LocationModel> getAll();
}
