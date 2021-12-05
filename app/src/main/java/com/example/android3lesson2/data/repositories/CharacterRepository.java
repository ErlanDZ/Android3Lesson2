package com.example.android3lesson2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.data.local.daos.CharacterDao;
import com.example.android3lesson2.data.network.apiservices.CharacterApiService;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    private final CharacterApiService service;
    private final CharacterDao characterDao;

    @Inject
    public CharacterRepository(CharacterApiService service, CharacterDao characterDao) {
        this.service = service;
        this.characterDao = characterDao;
    }

    public final MutableLiveData<RickAndMortyResponse<Character>> fetchCharacters(int page) {
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<Character>> data = new MutableLiveData<>();
        service.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                if (response.body() != null) {
                    characterDao.insert(response.body().getResults());
                    data.setValue(response.body());
                }
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
        service.fetchCharacter(id).enqueue(new Callback<Character>() {
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
        return characterDao.getAll();
    }

}
