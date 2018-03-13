package com.akashdubey.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by homepc on 13-03-2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TrailerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TrailerHolder extends RecyclerView.ViewHolder{

        public TrailerHolder(View itemView) {
            super(itemView);
        }
    }
}
