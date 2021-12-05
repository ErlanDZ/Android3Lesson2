package com.example.android3lesson2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.data.local.daos.LocationDao;
import com.example.android3lesson2.data.network.apiservices.LocationApiService;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.utils.App;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationRepository {

    private final LocationApiService service;
    private final LocationDao locationDao;

    @Inject
    public LocationRepository(LocationApiService service, LocationDao locationDao) {
        this.service = service;
        this.locationDao = locationDao;
    }

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    private final MutableLiveData<LocationModel> _location = new MutableLiveData<>();

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocations(int page) {
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        service.fetchLocations(page).enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                if (response.body() != null) {
                    locationDao.insert(response.body().getResults());
                    data.setValue(response.body());
                }
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
        service.fetchLocation(id).enqueue(new Callback<LocationModel>() {
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
        return locationDao.getAll();
    }

}
