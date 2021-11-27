package com.example.android3lesson2.ui.fragments.episode;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.adapters.EpisodeAdapter;
import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.databinding.FragmentEpisodeBinding;

import java.util.ArrayList;


public class EpisodeFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeBinding> {

    EpisodeAdapter adapter = new EpisodeAdapter();
    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, pastVisibleItems;
    private ArrayList<EpisodeModel> episodeModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialization() {
        layoutManager = new LinearLayoutManager(getContext());
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        binding.recyclerEpisode.setLayoutManager(layoutManager);
        binding.recyclerEpisode.setAdapter(adapter);
    }

    @Override
    protected void setupListeners() {
        adapter.setOnItemClickListener(id -> Navigation.findNavController(EpisodeFragment.this.requireView()).navigate(
                EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(id)
        ));
    }


    @Override
    protected void setUpObservers() {
        viewModel.loadingEpisode().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderEpisode.setVisibility(View.VISIBLE);
                binding.recyclerEpisode.setVisibility(View.GONE);
            } else {
                binding.loaderEpisode.setVisibility(View.GONE);
                binding.recyclerEpisode.setVisibility(View.VISIBLE);
            }
        });
        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RickAndMortyResponse<EpisodeModel> episode) {
                if (episode != null){
                    episodeModels.addAll(episode.getResults());
                adapter.submitList(episodeModels);
                String next = episode.getInfo().getNext();
                if (next != null) {
                    binding.recyclerEpisode.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (dy > 0) {

                                viewModel.loadingEpisode().observe(getViewLifecycleOwner(), isLoading -> {
                                    if (isLoading) {
                                        binding.loaderEpisode.setVisibility(View.GONE);
                                        binding.recyclerEpisode.setVisibility(View.VISIBLE);
                                        binding.loaderEpisodeBar.setVisibility(View.VISIBLE);
                                    } else {
                                        binding.loaderEpisodeBar.setVisibility(View.GONE);
                                    }
                                });
                                visibleItemCount = layoutManager.getChildCount();
                                totalItemCount = layoutManager.getItemCount();
                                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                                    viewModel.page++;
                                    viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), episodeModelRickAndMortyResponse -> {
                                        if (episodeModelRickAndMortyResponse != null) {
                                            episodeModels.addAll(episodeModelRickAndMortyResponse.getResults());
                                            adapter.submitList(episodeModels);
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}