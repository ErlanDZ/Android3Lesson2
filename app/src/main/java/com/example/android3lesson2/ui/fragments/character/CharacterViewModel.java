package com.example.android3lesson2.ui.fragments.character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

    public LiveData<RickAndMortyResponse<Character>> fetchCharacters() {
        MutableLiveData<RickAndMortyResponse<Character>> data = new MutableLiveData<>();
        App.characterApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
