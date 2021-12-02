package com.example.android3lesson2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.utils.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    private final MutableLiveData<LocationModel> _location = new MutableLiveData<>();

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocations(int page) {
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        App.locationApiService.fetchLocations(page).enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                App.locationDao.insert(response.body().getResults());
                data.setValue(response.body());
                _isLoading.setValue(false);

            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, Throwable t) {
                data.setValue(null);
                _isLoading.setValue(false);
            }
        });
        return data;
    }

    public MutableLiveData<LocationModel> fetchLocation(int id) {
        _isLoading.setValue(true);
        App.locationApiService.fetchLocation(id).enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                _location.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                _location.setValue(null);
                _isLoading.setValue(true);
            }
        });
        return _location;
    }

    public List<LocationModel> getLocations() {
        return App.locationDao.getAll();
    }

}
