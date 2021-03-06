package com.example.android3lesson2.ui.fragments.character.detail;

import androidx.lifecycle.LiveData;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.repositories.CharacterRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
@HiltViewModel
public class CharacterDetailVIewModel extends BaseViewModel {

    private final CharacterRepository characterRepository;

    @Inject
    public CharacterDetailVIewModel(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public LiveData<Character> fetchCharacter(int id) {
        return characterRepository.fetchCharacter(id);
    }

    public LiveData<Boolean> loadingCharacterDetail() {
        return characterRepository._isLoading;
    }

}
