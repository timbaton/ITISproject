package com.example.timur.itisproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Timur on 13.09.2017.
 */

public class ArticlePagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<Article> mArticles;
    private final static String EXTRA_CRIME_ID = "article_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_pager);

        mViewPager = (ViewPager) findViewById(R.id.article_pager_acticity_view_pager);
        mArticles = ArticleLab.get(this).getArticles();
        UUID articleId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Article article = mArticles.get(position);
                return ArticleFragment.newInstance(article.getArticleId());
            }

            @Override
            public int getCount() {
                return mArticles.size();
            }
        });

        for (int i = 0; i < mArticles.size(); i++) {
            if (mArticles.get(i).getArticleId().equals(articleId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, ArticlePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
}

