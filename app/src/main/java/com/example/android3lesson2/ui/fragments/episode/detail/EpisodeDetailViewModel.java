package com.example.android3lesson2.ui.fragments.episode.detail;

import androidx.lifecycle.LiveData;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.repositories.EpisodeRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EpisodeDetailViewModel extends BaseViewModel {

    private final EpisodeRepository episodeRepository;

    @Inject
    public EpisodeDetailViewModel(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public LiveData<EpisodeModel> fetchEpisode(int id) {
        return episodeRepository.fetchEpisode(id);
    }

    public LiveData<Boolean> loadingEpisodeDetail() {
        return episodeRepository._isLoading;
    }
}
