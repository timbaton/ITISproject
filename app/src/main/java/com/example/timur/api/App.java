package com.example.timur.api;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Timur on 17.09.2017.
 */

public class App  {

    private static ArticleApi sArticleApi;
    private static Retrofit retrofit;



    public static ArticleApi getApi() {
        if (sArticleApi == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://ksftx.pythonanywhere.com/") //Базовая часть адреса
                    .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                    .build();
            sArticleApi = retrofit.create(ArticleApi.class); //Создаем объект, при помощи которого будем выполнять запросы
        }
        return sArticleApi;
    }
}
