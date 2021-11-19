package com.example.android3lesson2.ui.fragments.location;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3lesson2.R;
import com.example.android3lesson2.adapters.LocationAdapter;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;
import com.example.android3lesson2.databinding.FragmentLocationBinding;


public class LocationFragment extends Fragment {

    FragmentLocationBinding binding;
    LocationViewModel viewModel;
    LocationAdapter adapter = new LocationAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
        setupViews();
        setUpObservers();
    }

    private void setUpObservers() {
        viewModel.fetchLocation().observe(getViewLifecycleOwner(), location -> {
            adapter.addListLoc(location.getResults());
        });
    }

    private void setupViews() {
        binding.recyclerLocation.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerLocation.setAdapter(adapter);
    }

    private void initialization() {
        viewModel= new ViewModelProvider(this).get(LocationViewModel.class);
    }
}