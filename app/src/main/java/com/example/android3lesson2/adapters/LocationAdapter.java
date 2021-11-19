package com.example.android3lesson2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.databinding.ItemLocationBinding;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    List<LocationModel> list = new ArrayList<>();



    public void addListLoc(List<LocationModel> models){
        this.list = models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext())
        , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ItemLocationBinding binding;

        public ViewHolder(ItemLocationBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (LocationModel model){
            binding.txtNameLocation.setText(model.getName());
            binding.txtDimensionLocation.setText(model.getDimension());
            binding.txtTypeLocation.setText(model.getType());
        }
    }
}
