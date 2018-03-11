package com.akashdubey.imdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akashdubey.imdb.MainActivity;
import com.akashdubey.imdb.R;
import com.akashdubey.imdb.model.MovieModel;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.akashdubey.imdb.MainActivity.movieAdapter;

/**
 * Created by homepc on 07-03-2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder> {

    private List<MovieModel> movieAdapterItem;

    public MovieAdapter(List<MovieModel> movieModelList) {
        this.movieAdapterItem = movieModelList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compact_view, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        final MovieModel movieModel = movieAdapterItem.get(position);
        Glide.with(holder.movieImage.getContext()).load(movieModel.getmMovieImage()).into(holder.movieImage);
        holder.movieTitle.setText(movieModel.getmTitle());
        holder.releaseDate.setText(movieModel.getmReleaseDate());
        holder.popularity.setText(movieModel.getmPopularity());
        holder.votesCount.setText(movieModel.getmVoteAverage() + "/ 10 voted by " + movieModel.getmVotesCount() + " ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LEGO","ID "+movieModel.getmId()+", NAME "+movieModel.getmTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return MovieModel.movieModelList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieTitle, releaseDate, popularity, votesCount;

        public MyHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.posterIV);
            movieTitle = itemView.findViewById(R.id.movieNameTV);
            releaseDate = itemView.findViewById(R.id.releasedateTV);
            popularity = itemView.findViewById(R.id.popularityTV);
            votesCount = itemView.findViewById(R.id.votesTV);
        }


    }
}
