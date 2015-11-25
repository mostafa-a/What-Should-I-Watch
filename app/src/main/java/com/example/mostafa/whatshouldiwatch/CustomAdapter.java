package com.example.mostafa.whatshouldiwatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private Response response;
    List<Response.ResultsEntity> resultsList;

    public CustomAdapter(Context mContext, Response response) {
        this.mContext = mContext;
        this.response = response;
        resultsList = response.getResults();
    }

    @Override
    public int getCount() {
        return response.getTotal_results();
    }

    @Override
    public Response.ResultsEntity getItem(int i) {
        return resultsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parentViewGroup) {

        //if(i==0) {
            Response.ResultsEntity resultsEntity = getItem(i);

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.movie_representation, parentViewGroup, false);
            }
            TextView movieTitle = (TextView) convertView.findViewById(R.id.movieTitle);
            TextView movieOverview = (TextView) convertView.findViewById(R.id.movieOverview);
            ImageView moviePoster = (ImageView) convertView.findViewById(R.id.moviePoster);

            movieTitle.setText(resultsEntity.getTitle());
            movieOverview.setText(resultsEntity.getOverview());
            String mImageBaseUrl = "http://image.tmdb.org/t/p/w500/";
            Picasso.with(mContext).load(mImageBaseUrl + resultsEntity.getPoster_path()).into(moviePoster);
        //}
        return convertView;

    }
}
