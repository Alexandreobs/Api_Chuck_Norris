package com.example.appchucknorris.model.repository;

import com.example.appchucknorris.model.pojos.Result;

import java.util.List;

import io.reactivex.Observable;

import static com.example.appchucknorris.model.data.remote.RetroFifService.getApiService;

public class ChuckRepository {

    public Observable<List<String>> getList() {
        return getApiService().getListaCategorias();
    }

    public Observable<Result> getListPiadas(String categoria) {
        return getApiService().getListaPiadas(categoria);
    }
}
