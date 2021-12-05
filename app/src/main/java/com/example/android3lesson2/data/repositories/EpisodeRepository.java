package com.example.android3lesson2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.data.local.daos.EpisodeDao;
import com.example.android3lesson2.data.network.apiservices.EpisodeApiService;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.utils.App;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    private final EpisodeApiService service;
    private final EpisodeDao episodeDao;

    @Inject
    public EpisodeRepository(EpisodeApiService service, EpisodeDao episodeDao) {
        this.service = service;
        this.episodeDao = episodeDao;
    }

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisodes(int page) {
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        service.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeModel>> call, Response<RickAndMortyResponse<EpisodeModel>> response) {
                if (response.body() != null) {
                    episodeDao.insert(response.body().getResults());
                    data.setValue(response.body());
                }
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.setValue(null);
                _isLoading.setValue(false);
            }
        });
        return data;
    }

    public MutableLiveData<EpisodeModel> fetchEpisode(int id) {
        MutableLiveData<EpisodeModel> _episode = new MutableLiveData<>();
        _isLoading.setValue(true);
        service.fetchEpisode(id).enqueue(new Callback<EpisodeModel>() {
            @Override
            public void onResponse(Call<EpisodeModel> call, Response<EpisodeModel> response) {
                _episode.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<EpisodeModel> call, Throwable t) {
                _episode.setValue(null);
                _isLoading.setValue(true);
            }
        });
        return _episode;
    }

    public List<EpisodeModel> getEpisodes() {
        return episodeDao.getAll();
    }

}
