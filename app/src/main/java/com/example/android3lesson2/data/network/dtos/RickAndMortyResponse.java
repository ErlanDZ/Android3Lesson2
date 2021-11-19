package com.example.android3lesson2.data.network.dtos;

import com.google.gson.annotations.SerializedName;

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

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
