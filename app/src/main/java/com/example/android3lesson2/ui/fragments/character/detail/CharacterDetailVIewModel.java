package com.example.android3lesson2.ui.fragments.character.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.repositories.CharacterRepository;

public class CharacterDetailVIewModel extends BaseViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();


    public LiveData<Character> fetchCharacter(int id) {
       return characterRepository.fetchCharacter(id);
    }

    public LiveData<Boolean>  loadingCharacterDetail(){
        return characterRepository._isLoading;
    }

}
