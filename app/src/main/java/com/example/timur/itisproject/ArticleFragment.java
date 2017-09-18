package com.example.timur.itisproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.UUID;

/**
 * Created by Timur on 13.09.2017.
 */

public class ArticleFragment extends Fragment{
    public static final String ARG_CRIME_ID = "crime_id";
    private Article mArticle;
    private TextView mArticleText;
    private TextView mTitle;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UUID uuid = (UUID) getArguments().get(ARG_CRIME_ID);
            mArticle = ArticleLab.get(getActivity()).getArticle(uuid);
        }
    }

    public static ArticleFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, uuid);
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_article, container, false);
        mArticleText = (TextView)v.findViewById(R.id.article_text);
        mArticleText.setText(mArticle.getText());

//        mTitle = (TextView)v.findViewById(R.id.title);
//        mTitle.setText(mArticle.getTitle());

        mImageView = (ImageView) v.findViewById(R.id.image);
        Picasso.with(getActivity()).load(mArticle.getImage()).fit().into(mImageView);



        return v;
    }

}
