package com.example.appchucknorris.model.data.remote;

import com.example.appchucknorris.model.pojos.Result;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChuckAPI {

    @GET("categories")
    Observable<List<String>> getListaCategorias();

    @GET("random")
    Observable<Result> getListaPiadas(
            @Query("category") String categoria);
}
