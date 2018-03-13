package com.akashdubey.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by homepc on 13-03-2018.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {


    @Override
    public CastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CastHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CastHolder extends RecyclerView.ViewHolder {
        public CastHolder(View itemView) {
            super(itemView);
        }
    }
}
