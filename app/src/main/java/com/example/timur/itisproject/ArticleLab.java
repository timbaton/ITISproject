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
        for (int i = 0; i < 100; i++) {
            Article article = new Article();
            article.setText("There must be Article' text. " +
                    "\n\nTimur Kam and Nick are the best ppl in the world" +
                    "\n\nWe hack the future" +
                    "\nI can't show you your real text because I don't have the api(((((" +
                    "\n" +
                    "\nI write this text cuz I have to check the scroll view))))" +
                    "\n" +
                    "\nIt seems like it's work yeah man fuck you" + i);
            article.setTitle("There must be Article' title" + i);
            mArticleList.add(article);
        }
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


}
