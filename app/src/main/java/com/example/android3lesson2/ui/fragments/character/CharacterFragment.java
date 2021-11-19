package com.example.android3lesson2.ui.fragments.character;

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
import com.example.android3lesson2.adapters.CharacterAdapter;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;


public class CharacterFragment extends Fragment {

    FragmentCharacterBinding binding;
    CharacterViewModel viewModel;
    private CharacterAdapter adapter = new CharacterAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(getLayoutInflater(),container,false);
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
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), character -> {
            adapter.addList(character.getResults());
        });
    }

    private void setupViews() {
        binding.recyclerCharacter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCharacter.setAdapter(adapter);
    }

    private void initialization() {
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}