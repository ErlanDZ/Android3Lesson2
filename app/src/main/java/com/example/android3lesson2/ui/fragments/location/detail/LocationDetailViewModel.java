package com.example.android3lesson2.ui.fragments.location.detail;

import androidx.lifecycle.LiveData;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.data.repositories.LocationRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationDetailViewModel extends BaseViewModel {

    private final LocationRepository locationRepository;

    @Inject
    public LocationDetailViewModel(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LiveData<LocationModel> fetchLocation(int id) {
        return locationRepository.fetchLocation(id);
    }

    public LiveData<Boolean> loadingLocation() {
        return locationRepository._isLoading;
    }

}
