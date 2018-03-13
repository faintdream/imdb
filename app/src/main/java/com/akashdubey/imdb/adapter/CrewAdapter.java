package com.akashdubey.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by homepc on 13-03-2018.
 */

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewHolder> {

    @Override
    public CrewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CrewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CrewHolder extends RecyclerView.ViewHolder{

        public CrewHolder(View itemView) {
            super(itemView);
        }
    }
}
