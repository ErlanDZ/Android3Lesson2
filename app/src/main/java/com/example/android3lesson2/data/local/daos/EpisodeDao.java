package com.example.android3lesson2.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<EpisodeModel> episodeModels);


    @Query("SELECT * FROM episodemodel LIMIT 1")
    List<EpisodeModel> getAnyRecipe();


    @Delete
    void delete(EpisodeModel episodeModel);

    @Query("SELECT * FROM episodemodel")
    List<EpisodeModel> getAll();
}
