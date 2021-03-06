package com.example.android3lesson2.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.example.android3lesson2.data.network.onItemClick.OnItemClick;
import com.example.android3lesson2.data.network.onItemClick.OnLongClick;
import com.example.android3lesson2.databinding.ItemCharacterBinding;

public class CharacterAdapter extends ListAdapter<Character, CharacterAdapter.ViewHolder> {


    public OnItemClick onItemClickCharacter;

    public OnLongClick onLongClick;

    public CharacterAdapter() {
        super(new CharacterDiffUtil());
    }


    public void setOnItemClickListener(OnItemClick listener) {
        this.onItemClickCharacter = listener;
    }

    public void setLongOnItemClickListener(OnLongClick onItemClick) {
        this.onLongClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static class CharacterDiffUtil extends DiffUtil.ItemCallback<Character> {
        @Override
        public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem == newItem;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCharacterBinding binding;

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
