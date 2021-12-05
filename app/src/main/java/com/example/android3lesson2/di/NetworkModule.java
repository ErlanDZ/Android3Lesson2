package com.example.android3lesson2.di;

import com.example.android3lesson2.data.network.RetrofitClient;
import com.example.android3lesson2.data.network.apiservices.CharacterApiService;
import com.example.android3lesson2.data.network.apiservices.EpisodeApiService;
import com.example.android3lesson2.data.network.apiservices.LocationApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    RetrofitClient retrofitClient = new RetrofitClient();

    @Singleton
    @Provides
    public CharacterApiService provideCharacterApiService() {
        return retrofitClient.provideCharacterApiService();
    }

    @Provides
    @Singleton
    public EpisodeApiService provideEpisodeApiService() {
        return retrofitClient.provideEpisodeApiService();
    }

    @Provides
    @Singleton
    public LocationApiService provideLocationApiService() {
        return retrofitClient.provideLocationApiService();
    }
}
