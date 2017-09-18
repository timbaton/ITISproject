package com.example.timur.itisproject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Timur on 13.09.2017.
 */

public class Article {

    public Article(){
        mArticleId = UUID.randomUUID();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getArticleId() {
        return mArticleId;
    }

    public void setArticleId(UUID articleId) {
        mArticleId = articleId;
    }

    private UUID mArticleId;
    private String text;
    private Date mDate;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

}
