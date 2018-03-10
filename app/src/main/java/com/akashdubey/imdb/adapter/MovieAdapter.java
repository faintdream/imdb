package com.akashdubey.imdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akashdubey.imdb.R;
import com.akashdubey.imdb.model.MovieModel;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by homepc on 07-03-2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder> {

    ImageView movieImage;
    TextView movieTitle, releaseDate, popularity, votesCount;
    private Context context;
    private List<MovieModel> movieAdapterItem;

//    public MovieAdapter(Context context, List<MovieModel> movieAdapterItem) {
//        this.context = context;
//        this.movieAdapterItem = movieAdapterItem;
//    }

    public MovieAdapter(List<MovieModel> movieModelList){
        this.movieAdapterItem=movieModelList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compact_view, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        MovieModel movieModel = movieAdapterItem.get(position);
        Glide.with(movieImage.getContext()).load(movieModel.getmMovieImage()).into(movieImage);
        movieTitle.setText(movieModel.getmTitle());
        releaseDate.setText(movieModel.getmReleaseDate());
        popularity.setText(movieModel.getmPopularity());
        votesCount.setText(movieModel.getmVoteAverage()+"/ 10 voted by "+ movieModel.getmVotesCount()+ " " );
    }

    @Override
    public int getItemCount() {
        return movieAdapterItem.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {


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
