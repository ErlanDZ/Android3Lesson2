package com.example.android3lesson2.ui.fragments.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {
    public LiveData<RickAndMortyResponse<LocationModel>> fetchLocation(){
        MutableLiveData<RickAndMortyResponse<LocationModel>> data= new MutableLiveData<>();
        App.locationApiService.fetchLocations().enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
