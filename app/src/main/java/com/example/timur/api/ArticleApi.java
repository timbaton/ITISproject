package com.example.timur.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleApi {
    @GET("api/posts/?format=json")
    Call<List<Pojo>> getData();
}