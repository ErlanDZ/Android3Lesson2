package com.example.android3lesson2.ui.fragments.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.base.BaseViewModel;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.data.repositories.LocationRepository;

public class LocationViewModel extends BaseViewModel {

    private final LocationRepository locationRepository = new LocationRepository();
    public int page = 1;

   public LiveData<RickAndMortyResponse<LocationModel>> fetchLocations(){
       return locationRepository.fetchLocations(page);
   }
   public LiveData<Boolean> loadingLocation(){
       return locationRepository._isLoading;
   }



}
