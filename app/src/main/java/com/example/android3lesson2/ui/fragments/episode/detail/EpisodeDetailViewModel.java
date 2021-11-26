package com.example.android3lesson2.ui.fragments.episode.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.repositories.EpisodeRepository;

public class EpisodeDetailViewModel extends BaseViewModel {

    private final EpisodeRepository episodeRepository = new EpisodeRepository();

    public LiveData<EpisodeModel> fetchEpisode(int id) {
        return episodeRepository.fetchEpisode(id);
    }

    public LiveData<Boolean> loadingEpisodeDetail(){
        return episodeRepository._isLoading;
    }
}
