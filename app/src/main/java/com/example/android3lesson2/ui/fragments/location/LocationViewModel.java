package com.example.android3lesson2.ui.fragments.location;


import androidx.lifecycle.LiveData;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.data.repositories.LocationRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationViewModel extends BaseViewModel {

    @Inject
    public LocationViewModel(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    private final LocationRepository locationRepository;
    public int page = 1;

    public LiveData<RickAndMortyResponse<LocationModel>> fetchLocations() {
        return locationRepository.fetchLocations(page);
    }

    public LiveData<Boolean> loadingLocation() {
        return locationRepository._isLoading;
    }

    List<LocationModel> getLocation() {
        return locationRepository.getLocations();
    }
}
