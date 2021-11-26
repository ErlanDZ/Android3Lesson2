package com.example.android3lesson2.ui.fragments.character.detail;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.R;
import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentCaracterDetailBinding;


public class CharacterDetailFragment extends BaseFragment<CharacterDetailVIewModel, FragmentCaracterDetailBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCaracterDetailBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    protected void initialization() {
        viewModel = new ViewModelProvider(this).get(CharacterDetailVIewModel.class);
    }

    @Override
    protected void setupViews() {
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(getArguments()).getId())
                .observe(getViewLifecycleOwner(), character -> {
                    Glide.with(binding.imageCharacterDetail)
                            .load(character.getImage())
                            .into(binding.imageCharacterDetail);

                    binding.txtIdCharacterDetail.setText(String.valueOf(character.getId()));
                    binding.txtNameCharacterDetail.setText(character.getName());
                    binding.txtStatusCharacterDetail.setText(character.getStatus());
                    binding.txtGenderCharacterDetail.setText(character.getGender());
                    binding.txtSpeciesCharacterDetail.setText(character.getSpecies());
                    if (character.getStatus() != null) {
                        switch (character.getStatus()) {
                            case "Alive":
                                binding.viewStatus.setBackgroundResource(R.drawable.oval);
                                break;
                            case "Dead":
                                binding.viewStatus.setBackgroundResource(R.drawable.oval1);
                                break;
                        }
                    }
                });
        viewModel.loadingCharacterDetail().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderCharacterDetail.setVisibility(View.VISIBLE);
                binding.txtIdCharacterDetail.setVisibility(View.GONE);
                binding.txtNameCharacterDetail.setVisibility(View.GONE);
                binding.txtStatusCharacterDetail.setVisibility(View.GONE);
                binding.imageCharacterDetail.setVisibility(View.GONE);
                binding.viewStatus.setVisibility(View.GONE);
                binding.gender.setVisibility(View.GONE);
                binding.hyphen.setVisibility(View.GONE);
                binding.id.setVisibility(View.GONE);
                binding.name.setVisibility(View.GONE);
                binding.txtSpeciesCharacterDetail.setVisibility(View.GONE);
            } else {
                binding.loaderCharacterDetail.setVisibility(View.GONE);
                binding.txtIdCharacterDetail.setVisibility(View.VISIBLE);
                binding.txtNameCharacterDetail.setVisibility(View.VISIBLE);
                binding.txtStatusCharacterDetail.setVisibility(View.VISIBLE);
                binding.imageCharacterDetail.setVisibility(View.VISIBLE);
                binding.viewStatus.setVisibility(View.VISIBLE);
                binding.gender.setVisibility(View.VISIBLE);
                binding.hyphen.setVisibility(View.VISIBLE);
                binding.id.setVisibility(View.VISIBLE);
                binding.name.setVisibility(View.VISIBLE);
                binding.txtSpeciesCharacterDetail.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}