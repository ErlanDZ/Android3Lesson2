package com.example.android3lesson2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.utils.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public final MutableLiveData<RickAndMortyResponse<Character>> fetchCharacters(int page) {
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<Character>> data = new MutableLiveData<>();
        App.characterApiService.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                App.characterDao.insert(response.body().getResults());
                data.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                data.setValue(null);
                _isLoading.setValue(true);
            }
        });
        return data;
    }


    public MutableLiveData<Character> fetchCharacter(int id) {
        MutableLiveData<Character> _character = new MutableLiveData<>();
        _isLoading.setValue(true);
        App.characterApiService.fetchCharacter(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                _character.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                _character.setValue(null);
                _isLoading.setValue(true);
            }
        });
        return _character;
    }

    public List<Character> getCharacters() {
        return App.characterDao.getAll();
    }

}
