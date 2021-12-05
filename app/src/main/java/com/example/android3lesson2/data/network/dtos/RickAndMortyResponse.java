package com.example.android3lesson2.data.network.dtos;

import com.example.android3lesson2.data.network.dtos.episode.EpisodeModel;
import com.example.android3lesson2.data.network.dtos.сharacter.Character;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RickAndMortyResponse<T> {

    @SerializedName("info")
    private Info info;

    @SerializedName("results")
    private List<T> results;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<T> getResults() {
        return (ArrayList<T>) results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
