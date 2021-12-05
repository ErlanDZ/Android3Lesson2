package com.example.android3lesson2.ui.fragments.location;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.databinding.FragmentLocationBinding;
import com.example.android3lesson2.ui.adapters.LocationAdapter;
import com.example.android3lesson2.utils.App;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {

    LocationAdapter adapter = new LocationAdapter();
    private final ArrayList<LocationModel> locationModels = new ArrayList<>();

    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, pastVisibleItems;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

        adapter.setOnItemClickListener(id -> {
            if (!isOnline()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                builder.setTitle(Html.fromHtml("<font color='#FF0000'>ФУНКЦИЯ НЕ ДОСТУПНА</font>"));
                builder.setMessage(Html.fromHtml("<font color='#FF0000'>ВКЛЮЧИТЕ ИНТЕРНЕТ!!!!</font>"));
                builder.show();

            } else {
                Navigation.findNavController(LocationFragment.this.
                        requireView()).navigate(LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id));
            }
        });

    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void setUpObservers() {
        if (!isOnline()) {
            if (viewModel.getLocation().isEmpty()) {
                Toast.makeText(getContext(), "ДАННЫХ НЕТ! ВКЛЮЧИТЕ ИНТЕРНЕТ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "OFF-LINE", Toast.LENGTH_SHORT).show();
                adapter.submitList(viewModel.getLocation());
            }
        } else {
            viewModel.fetchLocations().observe(getViewLifecycleOwner(), location -> {
                if (location != null) {
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
                                        if (!isOnline()){
                                            binding.loaderLocationBar.setVisibility(View.GONE);
                                            Toast.makeText(getContext(), "OFF-LINE", Toast.LENGTH_SHORT).show();
                                        }else {
                                            viewModel.page++;
                                            viewModel.fetchLocations().observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse -> {
                                                if (locationModelRickAndMortyResponse != null) {
                                                    locationModels.addAll(locationModelRickAndMortyResponse.getResults());
                                                    adapter.submitList(locationModels);
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            });
            viewModel.loadingLocation().observe(getViewLifecycleOwner(), isLoading -> {
                if (isLoading) {
                    binding.loaderLocation.setVisibility(View.VISIBLE);
                    binding.recyclerLocation.setVisibility(View.GONE);
                } else {
                    binding.loaderLocation.setVisibility(View.GONE);
                    binding.recyclerLocation.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}