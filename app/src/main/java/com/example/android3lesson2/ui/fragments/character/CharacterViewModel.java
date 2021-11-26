package com.example.android3lesson2.ui.fragments.character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.repositories.CharacterRepository;

public class CharacterViewModel extends BaseViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();
    public int page = 1;


    public LiveData<RickAndMortyResponse<Character>> fetchCharacters() {
        return characterRepository.fetchCharacters(page);
    }
    public LiveData<Boolean> loadingCharacter(){
        return characterRepository._isLoading;
    }


}
