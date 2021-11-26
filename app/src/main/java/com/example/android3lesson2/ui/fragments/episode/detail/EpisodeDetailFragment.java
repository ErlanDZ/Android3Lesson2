package com.example.android3lesson2.ui.fragments.episode.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentEpisodeDetailBinding;

public class EpisodeDetailFragment extends BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeDetailBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialization() {
        viewModel = new ViewModelProvider(this).get(EpisodeDetailViewModel.class);
    }

    @Override
    protected void setupViews() {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(getArguments()).getId())
                .observe(getViewLifecycleOwner(), episodeModel -> {
                    binding.txtIdDetail.setText(String.valueOf(episodeModel.getId()));
                    binding.txtNameEpisodeDetail.setText(episodeModel.getName());
                    binding.txtAirDateEpisodeDetail.setText(episodeModel.getAir_date());
                    binding.txtEpisodeEpisodeDetail.setText(episodeModel.getEpisode());
                    binding.txtCreatedEpisodeDetail.setText(episodeModel.getCreated());
                });

        viewModel.loadingEpisodeDetail().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderEpisodeDetail.setVisibility(View.VISIBLE);
                binding.txtIdDetail.setVisibility(View.GONE);
                binding.txtNameEpisodeDetail.setVisibility(View.GONE);
                binding.txtAirDateEpisodeDetail.setVisibility(View.GONE);
                binding.txtEpisodeEpisodeDetail.setVisibility(View.GONE);
                binding.txtCreatedEpisodeDetail.setVisibility(View.GONE);
                binding.id.setVisibility(View.GONE);
                binding.name.setVisibility(View.GONE);
                binding.episode.setVisibility(View.GONE);
                binding.airDate.setVisibility(View.GONE);
                binding.created.setVisibility(View.GONE);

            } else {
                binding.loaderEpisodeDetail.setVisibility(View.GONE);
                binding.txtIdDetail.setVisibility(View.VISIBLE);
                binding.txtNameEpisodeDetail.setVisibility(View.VISIBLE);
                binding.txtAirDateEpisodeDetail.setVisibility(View.VISIBLE);
                binding.txtEpisodeEpisodeDetail.setVisibility(View.VISIBLE);
                binding.txtCreatedEpisodeDetail.setVisibility(View.VISIBLE);
                binding.id.setVisibility(View.VISIBLE);
                binding.name.setVisibility(View.VISIBLE);
                binding.episode.setVisibility(View.VISIBLE);
                binding.airDate.setVisibility(View.VISIBLE);
                binding.created.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}