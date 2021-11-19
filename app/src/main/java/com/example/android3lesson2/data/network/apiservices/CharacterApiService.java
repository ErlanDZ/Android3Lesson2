package com.example.android3lesson2.data.network.apiservices;

import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterApiService {

    @GET("/api/character")
    Call<RickAndMortyResponse<Character>> fetchCharacters();
}
