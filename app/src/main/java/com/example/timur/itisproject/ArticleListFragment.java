package com.example.timur.itisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
;

import com.example.timur.api.App;
import com.example.timur.api.Pojo;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Timur on 13.09.2017.
 */

public class ArticleListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArticleAdapter mArticleAdapter;
    ArticleLab articleLab = ArticleLab.get(getActivity());
    List<Article> articles = articleLab.getArticles();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_article_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();


        App.getApi().getData().enqueue(new Callback<List<Pojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Pojo>> call, @NonNull Response<List<Pojo>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    Article article = new Article();
                    article.setTitle(response.body().get(i).getTitle());
                    article.setText(response.body().get(i).getText());
                    article.setImage(response.body().get(i).getImg());
                    articleLab.addArticle(article);
                }

                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pojo>> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }



    private void updateUI() {
        articleLab.clear();
        mArticleAdapter = new ArticleAdapter(articles);
        mRecyclerView.setAdapter(mArticleAdapter);
    }

    class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTitleTextView;
        ImageView mImageView;
        Article mArticle;

        ArticleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.article_title);
            mImageView = (ImageView) itemView.findViewById(R.id.action_image);
        }

        @Override
        public void onClick(View v) {
            Intent intent = ArticlePagerActivity.newIntent(getActivity(), mArticle.getArticleId());
            startActivity(intent);
        }

        public void bindArticle(Article article) {
            mArticle = article;
            mTitleTextView.setText(mArticle.getTitle());
            Picasso.with(getActivity()).load(mArticle.getImage()).fit().into(mImageView);
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
