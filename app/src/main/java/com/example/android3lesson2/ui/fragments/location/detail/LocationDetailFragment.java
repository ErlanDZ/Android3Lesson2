package com.example.android3lesson2.ui.fragments.location.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentLocationDetailBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationDetailFragment extends BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding> {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationDetailBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialization() {
        viewModel = new ViewModelProvider(this).get(LocationDetailViewModel.class);
    }

    @Override
    protected void setupViews() {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(getArguments()).getId())
                .observe(getViewLifecycleOwner(), model -> {
                    binding.txtIdLocationDetail.setText(String.valueOf(model.getId()));
                    binding.txtNameLocationDetail.setText(model.getName());
                    binding.txtTypeLocationDetail.setText(model.getType());
                    binding.txtDimensionLocationDetail.setText(model.getDimension());
                });
        viewModel.loadingLocation().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderLocationDetail.setVisibility(View.VISIBLE);
                binding.txtIdLocationDetail.setVisibility(View.GONE);
                binding.txtNameLocationDetail.setVisibility(View.GONE);
                binding.txtTypeLocationDetail.setVisibility(View.GONE);
                binding.txtDimensionLocationDetail.setVisibility(View.GONE);
                binding.id.setVisibility(View.GONE);
                binding.name.setVisibility(View.GONE);
                binding.dimension.setVisibility(View.GONE);
                binding.type.setVisibility(View.GONE);
            } else {
                binding.loaderLocationDetail.setVisibility(View.GONE);
                binding.txtIdLocationDetail.setVisibility(View.VISIBLE);
                binding.txtNameLocationDetail.setVisibility(View.VISIBLE);
                binding.txtTypeLocationDetail.setVisibility(View.VISIBLE);
                binding.txtDimensionLocationDetail.setVisibility(View.VISIBLE);
                binding.id.setVisibility(View.VISIBLE);
                binding.name.setVisibility(View.VISIBLE);
                binding.dimension.setVisibility(View.VISIBLE);
                binding.type.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}