package com.example.android3lesson2.ui.fragments.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.adapters.LocationAdapter;
import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.databinding.FragmentLocationBinding;

import java.util.ArrayList;


public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {

    LocationAdapter adapter = new LocationAdapter();
    private ArrayList<LocationModel> locationModels = new ArrayList<>();

    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, pastVisibleItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialization() {
        layoutManager = new LinearLayoutManager(getContext());
        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        binding.recyclerLocation.setLayoutManager(layoutManager);
        binding.recyclerLocation.setAdapter(adapter);
    }

    @Override
    protected void setupListeners() {
        adapter.setOnItemClickListener(id -> Navigation.findNavController(LocationFragment.this.
                requireView()).navigate(LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)));
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void setUpObservers() {
        viewModel.loadingLocation().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderLocation.setVisibility(View.VISIBLE);
                binding.recyclerLocation.setVisibility(View.GONE);
            } else {
                binding.loaderLocation.setVisibility(View.GONE);
                binding.recyclerLocation.setVisibility(View.VISIBLE);
            }
        });
        viewModel.fetchLocations().observe(getViewLifecycleOwner(), location -> {
            if (location != null){
                locationModels.addAll(location.getResults());
                adapter.submitList(locationModels);
                String next = location.getInfo().getNext();
                if (next != null) {
                    binding.recyclerLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (dy > 0) {
                                viewModel.loadingLocation().observe(getViewLifecycleOwner(), isLoading -> {
                                    if (isLoading) {
                                        binding.loaderLocation.setVisibility(View.GONE);
                                        binding.recyclerLocation.setVisibility(View.VISIBLE);
                                        binding.loaderLocationBar.setVisibility(View.VISIBLE);
                                    } else {
                                        binding.loaderLocationBar.setVisibility(View.GONE);
                                    }
                                });
                                visibleItemCount = layoutManager.getChildCount();
                                totalItemCount = layoutManager.getItemCount();
                                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                                    viewModel.page++;
                                    viewModel.fetchLocations().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<LocationModel>>() {
                                        @Override
                                        public void onChanged(RickAndMortyResponse<LocationModel> locationModelRickAndMortyResponse) {
                                            if (locationModelRickAndMortyResponse != null) {
                                                locationModels.addAll(locationModelRickAndMortyResponse.getResults());
                                                adapter.submitList(locationModels);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}