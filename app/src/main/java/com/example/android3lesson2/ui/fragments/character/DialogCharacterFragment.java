package com.example.android3lesson2.ui.fragments.character;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.databinding.FragmentDialogCharacterBinding;

public class DialogCharacterFragment extends DialogFragment {
    FragmentDialogCharacterBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = FragmentDialogCharacterBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog builder = new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .create();
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setupArgs();
        return builder;
    }

    private void setupArgs() {
        Glide.with(binding.imageDialog)
                .load(DialogCharacterFragmentArgs.fromBundle(getArguments()).getImage())
                .into(binding.imageDialog);
    }
}
