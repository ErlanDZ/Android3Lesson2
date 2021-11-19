package com.example.android3lesson2.ui.fragments.episode;

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
import com.example.android3lesson2.adapters.EpisodeAdapter;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;
import com.example.android3lesson2.databinding.FragmentEpisodeBinding;


public class EpisodeFragment extends Fragment {

    FragmentEpisodeBinding binding;
    EpisodeViewModel viewModel;
    EpisodeAdapter adapter = new EpisodeAdapter();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(getLayoutInflater(),container,false);
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
        viewModel.fetchEpisode().observe(getViewLifecycleOwner(), episode -> {
            adapter.addList(episode.getResults());
        });
    }

    private void setupViews() {
        binding.recyclerEpisode.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerEpisode.setAdapter(adapter);
    }

    private void initialization() {
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
    }
}