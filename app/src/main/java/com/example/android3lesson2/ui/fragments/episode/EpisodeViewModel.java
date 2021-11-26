package com.example.android3lesson2.ui.fragments.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.repositories.EpisodeRepository;

public class EpisodeViewModel extends BaseViewModel {
    private final EpisodeRepository episodeRepository = new EpisodeRepository();
    public int page = 1;

    public LiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisodes(){
        return episodeRepository.fetchEpisodes(page);
    }

    public LiveData<Boolean> loadingEpisode(){
        return episodeRepository._isLoading;
    }
}
