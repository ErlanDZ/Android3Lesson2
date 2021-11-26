package com.example.android3lesson2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.network.onItemClick.OnItemClick;
import com.example.android3lesson2.data.network.onItemClick.OnLongClick;
import com.example.android3lesson2.databinding.ItemCharacterBinding;
import com.example.android3lesson2.ui.fragments.character.DialogCharacterFragment;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    
    List<Character> list = new ArrayList<>();
    public OnItemClick onItemClickCharacter;

    public OnLongClick onLongClick;



    public void addList(List<Character> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.onItemClickCharacter = listener;
    }

    public void setLongOnItemClickListener(OnLongClick onItemClick){
        this.onLongClick = onItemClick;
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

               binding.getRoot().setOnClickListener(view -> {
                   onItemClickCharacter.onClickItemListener(character.getId());
               });

               binding.getRoot().setOnLongClickListener(view -> {
                   onLongClick.onLongClickItemListener(getAdapterPosition(), character);
                   return false;
               });

        }
    }
}
