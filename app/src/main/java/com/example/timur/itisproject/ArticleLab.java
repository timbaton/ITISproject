package com.example.timur.itisproject;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Timur on 13.09.2017.
 */

public class ArticleLab {

    private static ArticleLab sArticleLab;
    private List<Article> mArticleList;

    public static ArticleLab get(Context context){
        if (sArticleLab == null) {
            sArticleLab = new ArticleLab(context);
        }
        return sArticleLab;
    }

    private ArticleLab(Context context) {
        mArticleList = new ArrayList<>();

    }

    public void addArticle(Article article){
        mArticleList.add(article);
    }

    public List<Article> getArticles() {
        return mArticleList;
    }

    public Article getArticle(UUID uuid) {
        for (Article crime : mArticleList) {
            if (crime.getArticleId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

    public void clear(){
        mArticleList.clear();
    }


}
