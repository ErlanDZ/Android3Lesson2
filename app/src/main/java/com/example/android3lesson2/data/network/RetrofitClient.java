package com.example.android3lesson2.data.network;

import com.example.android3lesson2.data.network.apiservices.CharacterApiService;
import com.example.android3lesson2.data.network.apiservices.EpisodeApiService;
import com.example.android3lesson2.data.network.apiservices.LocationApiService;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .build();

    private HttpLoggingInterceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    public CharacterApiService provideCharacterApiService(){
        return retrofit.create(CharacterApiService.class);
    }

    public LocationApiService provideLocationApiService(){
        return retrofit.create(LocationApiService.class);
    }

    public EpisodeApiService provideEpisodeApiService (){
        return retrofit.create(EpisodeApiService.class);
    }
}
