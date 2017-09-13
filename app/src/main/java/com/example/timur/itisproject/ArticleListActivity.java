package com.example.timur.itisproject;

import android.support.v4.app.Fragment;

/**
 * Created by Timur on 13.09.2017.
 */

public class ArticleListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ArticleListFragment();
    }
}

