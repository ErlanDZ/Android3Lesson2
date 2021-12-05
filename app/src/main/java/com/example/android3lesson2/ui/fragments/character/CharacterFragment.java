package com.example.android3lesson2.ui.fragments.character;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.network.onItemClick.OnItemClick;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;
import com.example.android3lesson2.ui.adapters.CharacterAdapter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    private final CharacterAdapter adapter = new CharacterAdapter();
    private final ArrayList<Character> characters = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, pastVisibleItems;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
                if (!isOnline()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>ФУНКЦИЯ НЕ ДОСТУПНА</font>"));
                    builder.setMessage(Html.fromHtml("<font color='#FF0000'>ВКЛЮЧИТЕ ИНТЕРНЕТ!!!!</font>"));
                    builder.show();
                } else {
                    Navigation.findNavController(CharacterFragment.this.requireView()).navigate(
                            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(id)
                    );
                }
            }
        });


        adapter.setLongOnItemClickListener((position, model) -> Navigation
                .findNavController(CharacterFragment.this.requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToDialogCharacterFragment(model.getImage())));
    }


    @Override
    protected void setUpObservers() {
        if (!isOnline()) {
            if (viewModel.getCharacter().isEmpty()) {
                Toast.makeText(getContext(), "ДАННЫХ НЕТ! ВКЛЮЧИТЕ ИНТЕРНЕТ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "OFF-LINE", Toast.LENGTH_SHORT).show();
                adapter.submitList(viewModel.getCharacter());

            }
        } else {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), character -> {
                if (character != null) {
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
                                        if (!isOnline()){
                                            binding.loaderCharacterBar.setVisibility(View.GONE);
                                            Toast.makeText(getContext(), "OFF-LINE", Toast.LENGTH_SHORT).show();
                                        }else {
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
                            }
                        });

                    }
                }
            });
            viewModel.loadingCharacter().observe(getViewLifecycleOwner(), isLoading -> {
                if (isLoading) {
                    binding.loaderCharacter.setVisibility(View.VISIBLE);
                    binding.recyclerCharacter.setVisibility(View.GONE);
                } else {
                    binding.loaderCharacter.setVisibility(View.GONE);
                    binding.recyclerCharacter.setVisibility(View.VISIBLE);
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