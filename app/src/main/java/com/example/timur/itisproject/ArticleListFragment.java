package com.example.timur.itisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
;

import java.util.List;

/**
 * Created by Timur on 13.09.2017.
 */

public class ArticleListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArticleAdapter mArticleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_article_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        ArticleLab articleLab = ArticleLab.get(getActivity());
        List<Article> articles = articleLab.getArticles();
        mArticleAdapter = new ArticleAdapter(articles);
        mRecyclerView.setAdapter(mArticleAdapter);
    }

    class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTitleTextView;
        Article mArticle;

        ArticleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.article_title);
        }

        @Override
        public void onClick(View v) {
            Intent intent = ArticlePagerActivity.newIntent(getActivity(), mArticle.getArticleId());
            startActivity(intent);
        }

        public void bindArticle(Article article) {
            mArticle = article;
            mTitleTextView.setText(mArticle.getTitle());
        }
    }

    private class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {

        private List<Article> mArticles;

        public ArticleAdapter(List<Article> articles){
            mArticles = articles;
        }

        @Override
        public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.row, parent, false);
            return new ArticleHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticleHolder holder, int position) {
            Article article = mArticles.get(position);
            holder.bindArticle(article);
        }

        @Override
        public int getItemCount() {
            return mArticles.size();
        }
    }
}
