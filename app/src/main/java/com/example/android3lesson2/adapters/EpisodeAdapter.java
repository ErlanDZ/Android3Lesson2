package com.example.android3lesson2.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.databinding.ItemEpisodeBinding;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    List<EpisodeModel> list = new ArrayList<>();

    public void addList(List<EpisodeModel> models){
        this.list = models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEpisodeBinding binding;

        public ViewHolder(ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (EpisodeModel model){
            binding.txtNameEpisode.setText(model.getName());
            binding.txtAirDateEpisode.setText(model.getAir_date());
            binding.txtEpisodeEpisode.setText(model.getEpisode());
            binding.txtUrlEpisode.setText(model.getUrl());
            binding.txtCreatedEpisode.setText(model.getCreated());
        }
    }
}
