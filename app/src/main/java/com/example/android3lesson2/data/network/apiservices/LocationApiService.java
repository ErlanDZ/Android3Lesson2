package com.example.android3lesson2.data.network.apiservices;

import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiService {
    @GET("/api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocations();
}
