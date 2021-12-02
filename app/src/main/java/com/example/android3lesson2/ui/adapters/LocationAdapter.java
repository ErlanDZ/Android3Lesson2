package com.example.android3lesson2.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.data.network.dtos.location.LocationModel;
import com.example.android3lesson2.data.network.onItemClick.OnItemClick;
import com.example.android3lesson2.databinding.ItemLocationBinding;

public class LocationAdapter extends ListAdapter<LocationModel,LocationAdapter.ViewHolder> {


    public OnItemClick onItemClickLocation ;

    public LocationAdapter(){
        super(new LocationDiffUtil());
    }


    public void setOnItemClickListener(OnItemClick listener){
        this.onItemClickLocation = listener;
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext())
        , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public static class LocationDiffUtil extends DiffUtil.ItemCallback<LocationModel>{

        @Override
        public boolean areItemsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem == newItem;
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ItemLocationBinding binding;

        public ViewHolder(ItemLocationBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (LocationModel model){
            binding.txtNameLocation.setText(model.getName());
            binding.txtTypeLocation.setText(model.getType());

            binding.getRoot().setOnClickListener(view -> {
                onItemClickLocation.onClickItemListener(model.getId());
            });
        }
    }
}
