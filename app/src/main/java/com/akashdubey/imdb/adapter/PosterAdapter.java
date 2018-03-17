package com.akashdubey.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akashdubey.imdb.R;
import com.akashdubey.imdb.model.MovieDetailsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Here we handle display of posters in recylerview with horizontal positioning
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterHolder> {

    List<MovieDetailsModel> posterAdapterList = new ArrayList<>();

    public PosterAdapter(List<MovieDetailsModel> posterModelList) {
        this.posterAdapterList=posterModelList;
    }

    @Override
    public PosterHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poster_view,parent,false);

        return new PosterHolder(view);
    }

    @Override
    public void onBindViewHolder(PosterHolder holder, int position) {
        MovieDetailsModel movieDetailsModel=posterAdapterList.get(position);

        if (movieDetailsModel.getmPosterImage().equals("http://image.tmdb.org/t/p/w45null")) {
            holder.poster.setImageResource(R.drawable.no_image);
        }else{
            Glide.with(holder.poster.getContext()).load(movieDetailsModel.getmPosterImage()).into(holder.poster);
        }

    }

    @Override
    public int getItemCount() {
        return posterAdapterList.size();
    }

    public class PosterHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        public PosterHolder(View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.dtlPosterIV);
        }
    }
}
