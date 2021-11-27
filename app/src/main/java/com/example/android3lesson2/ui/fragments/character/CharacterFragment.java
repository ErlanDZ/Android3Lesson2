package com.example.android3lesson2.ui.fragments.character;

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

import com.example.android3lesson2.adapters.CharacterAdapter;
import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.data.network.dtos.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.network.onItemClick.OnItemClick;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;

import java.util.ArrayList;


public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    private final CharacterAdapter adapter = new CharacterAdapter();
    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, pastVisibleItems;
    private ArrayList<Character> characters = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialization() {
        layoutManager = new LinearLayoutManager(getContext());
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        binding.recyclerCharacter.setLayoutManager(layoutManager);
        binding.recyclerCharacter.setAdapter(adapter);
    }

    @Override
    protected void setupListeners() {
        adapter.setOnItemClickListener(new OnItemClick() {
            @Override
            public void onClickItemListener(int id) {
                Navigation.findNavController(CharacterFragment.this.requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(id)
                );
            }
        });

        adapter.setLongOnItemClickListener((position, model) -> Navigation
                .findNavController(CharacterFragment.this.requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToDialogCharacterFragment(model.getImage())));
    }


    @Override
    protected void setUpObservers() {
        viewModel.loadingCharacter().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderCharacter.setVisibility(View.VISIBLE);
                binding.recyclerCharacter.setVisibility(View.GONE);
            } else {
                binding.loaderCharacter.setVisibility(View.GONE);
                binding.recyclerCharacter.setVisibility(View.VISIBLE);
            }
        });

        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), character -> {
            if (character != null){
                characters.addAll(character.getResults());
                adapter.submitList(characters);
            String next = character.getInfo().getNext();
            if (next != null) {
                binding.recyclerCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (dy > 0) {
                            viewModel.loadingCharacter().observe(getViewLifecycleOwner(), isLoading -> {
                                if (isLoading) {
                                    binding.loaderCharacter.setVisibility(View.GONE);
                                    binding.recyclerCharacter.setVisibility(View.VISIBLE);
                                    binding.loaderCharacterBar.setVisibility(View.VISIBLE);
                                } else {
                                    binding.loaderCharacterBar.setVisibility(View.GONE);
                                }
                            });
                            visibleItemCount = layoutManager.getChildCount();
                            totalItemCount = layoutManager.getItemCount();
                            pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                                viewModel.page++;
                                viewModel.fetchCharacters().observe(getViewLifecycleOwner(), character1 -> {
                                    if (character1 != null) {
                                        characters.addAll(character1.getResults());
                                        adapter.submitList(characters);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}