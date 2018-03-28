package com.akashdubey.imdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akashdubey.imdb.DetailsScreen;
import com.akashdubey.imdb.MainActivity;
import com.akashdubey.imdb.R;
import com.akashdubey.imdb.model.MovieModel;
import com.bumptech.glide.Glide;

import java.util.List;

//import static com.akashdubey.imdb.MainActivity.movieAdapter;

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
    public void onBindViewHolder(final MyHolder holder, final int position) {

        final MovieModel movieModel = movieAdapterItem.get(position);
        Glide.with(holder.movieImage.getContext()).load(movieModel.getmMovieImage()).into(holder.movieImage);
        holder.movieTitle.setText(movieModel.getmTitle());
        holder.releaseDate.setText(movieModel.getmReleaseDate());
        holder.votesCount.setText(movieModel.getmVoteAverage() + "/ 10 voted by " + movieModel.getmVotesCount() + " ");
        holder.ratings.setRating(Float.parseFloat(movieModel.getmVoteAverage().toString()) / 2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LEGO", "ID " + movieModel.getmId() + ", NAME " + movieModel.getmTitle());
                jumpScreen(holder.itemView, movieModel.getmId().toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return MovieModel.movieModelList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        RatingBar ratings;
        ImageView movieImage;
        TextView movieTitle, releaseDate, popularity, votesCount;

        public MyHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.posterIV);
            movieTitle = itemView.findViewById(R.id.movieNameTV);
            releaseDate = itemView.findViewById(R.id.releasedateTV);
            votesCount = itemView.findViewById(R.id.votesTV);
            ratings = itemView.findViewById(R.id.ratingsRB);
        }


    }

    public void jumpScreen(View view, String movieId) {
        Intent intent = new Intent(view.getContext(), DetailsScreen.class);
        Bundle bundle = new Bundle();
        bundle.putString("movieId", movieId);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);

    }
}


