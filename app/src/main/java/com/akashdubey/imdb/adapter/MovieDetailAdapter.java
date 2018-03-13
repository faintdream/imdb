package com.akashdubey.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by homepc on 13-03-2018.
 */

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailHolder> {


    @Override
    public MovieDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieDetailHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieDetailHolder extends RecyclerView.ViewHolder{

        public MovieDetailHolder(View itemView) {
            super(itemView);
        }
    }
}
