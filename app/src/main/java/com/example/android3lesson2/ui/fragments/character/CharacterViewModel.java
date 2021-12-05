package com.example.android3lesson2.ui.fragments.character;

import androidx.lifecycle.LiveData;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.repositories.CharacterRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterViewModel extends BaseViewModel {

    private final  CharacterRepository repository;

    @Inject
    public CharacterViewModel(CharacterRepository repository) {
        this.repository = repository;
    }

    public int page = 1;


    public LiveData<RickAndMortyResponse<Character>> fetchCharacters() {
        return repository.fetchCharacters(page);
    }

    public LiveData<Boolean> loadingCharacter() {
        return repository._isLoading;
    }

    List<Character> getCharacter() {
        return repository.getCharacters();
    }

}
