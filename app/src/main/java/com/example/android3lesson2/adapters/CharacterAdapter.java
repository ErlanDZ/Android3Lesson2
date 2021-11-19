package com.example.android3lesson2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.databinding.ItemCharacterBinding;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    
    List<Character> list = new ArrayList<>();


    public void addList(List<Character> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false));

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ItemCharacterBinding binding;

        public ViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Character character) {
            binding.txtNameCharacter.setText(character.getName());
            Glide.with(binding.imageCharacter)
                    .load(character.getImage())
                    .into(binding.imageCharacter);
        }
    }
}
