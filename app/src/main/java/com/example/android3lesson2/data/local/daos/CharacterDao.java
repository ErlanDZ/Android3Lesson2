package com.example.android3lesson2.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android3lesson2.data.network.dtos.сharacter.Character;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<Character> character);


    @Query("SELECT * FROM character LIMIT 1")
    List<Character> getAnyRecipe();


    @Delete
    void delete(Character character);

    @Query("SELECT * FROM character")
    List<Character> getAll();
}
