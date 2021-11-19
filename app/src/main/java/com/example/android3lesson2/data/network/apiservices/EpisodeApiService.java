package com.example.android3lesson2.data.network.apiservices;

import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EpisodeApiService {
    @GET("/api/episode")
    Call<RickAndMortyResponse<EpisodeModel>> fetchEpisodes();
}
