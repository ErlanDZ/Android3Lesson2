package com.example.android3lesson2.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.network.onItemClick.OnItemClick;
import com.example.android3lesson2.databinding.ItemEpisodeBinding;

public class EpisodeAdapter extends ListAdapter<EpisodeModel, EpisodeAdapter.ViewHolder> {


    public OnItemClick onItemClickEpisode;

    public EpisodeAdapter() {
        super(new EpisodeDiffUtil());
    }


    public void setOnItemClickListener(OnItemClick listener) {
        this.onItemClickEpisode = listener;
    }

    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static class EpisodeDiffUtil extends DiffUtil.ItemCallback<EpisodeModel> {
        @Override
        public boolean areItemsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return oldItem == newItem;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEpisodeBinding binding;

        public ViewHolder(ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(EpisodeModel model) {
            binding.txtNameEpisode.setText(model.getName());
            binding.txtAirDateEpisode.setText(model.getAir_date());

            binding.getRoot().setOnClickListener(view -> {
                onItemClickEpisode.onClickItemListener(model.getId());
            });
        }
    }
}
