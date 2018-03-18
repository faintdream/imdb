package com.akashdubey.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.akashdubey.imdb.R;
import com.akashdubey.imdb.model.MovieDetailsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homepc on 13-03-2018.
 */

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailHolder> {
    List<MovieDetailsModel> movieDetailAdapterList = new ArrayList<>();
    public MovieDetailAdapter(List<MovieDetailsModel> movieDetailsModels) {
        this.movieDetailAdapterList =movieDetailsModels;
    }

    @Override
    public MovieDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_detail_view,parent,false);
        return new MovieDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieDetailHolder holder, int position) {
        MovieDetailsModel movieDetailsModel=movieDetailAdapterList.get(position);
        String movieTitle=movieDetailsModel.getmTitle().toString();
        Integer length;
        if (movieTitle.length()>100) {
            length = 100;
        }else{
            length = 50;
        }
        Glide.with(holder.movieImage.getContext()).load(movieDetailsModel.getmMovieImage()).into(holder.movieImage);
        holder.movieTitle.setText(movieDetailsModel.getmTitle());
        holder.rating.setRating(Float.parseFloat(movieDetailsModel.getmVoteCount()));
        holder.movieOverview.setText(movieDetailsModel.getmOverview().substring(0,length)+"...");
        holder.movieFullOverview.setText(movieDetailsModel.getmOverview());
        holder.movieVoteAverage.setText("("+movieDetailsModel.getmVoteAverage()+"/10)");
        holder.movieVoteCount.setText(movieDetailsModel.getmVoteCount()+" users");
        holder.movieReleaseDate.setText(movieDetailsModel.getmReleaseDate());
        holder.movieBudget.setText("Budget: "+movieDetailsModel.getmBudget());
        holder.movieRevenue.setText("Revenue: "+movieDetailsModel.getmRevenue());
        holder.movieReleaseStatus.setText(movieDetailsModel.getmReleaseStatus());

    }

    @Override
    public int getItemCount() {

        return movieDetailAdapterList.size();
    }

    public class MovieDetailHolder extends RecyclerView.ViewHolder{
        ImageView movieImage,movieFavourite,movieWatchLater;
        RatingBar rating;
        TextView movieTitle,movieOverview,movieReleaseDate,movieBudget,movieRevenue,
        movieFullOverview,movieVoteCount,movieVoteAverage,movieReleaseStatus;
        public MovieDetailHolder(View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.dtlPosterIV);
            movieTitle=itemView.findViewById(R.id.dtlMovieTitleTV);
            movieReleaseDate=itemView.findViewById(R.id.dtlReleaseDateTV);
            movieBudget=itemView.findViewById(R.id.dtlBudgetTV);
            movieRevenue=itemView.findViewById(R.id.dtlRevenueTV);
            rating=itemView.findViewById(R.id.dtlRatingsRB);
            movieVoteAverage=itemView.findViewById(R.id.dtlVoteAverageTV);
            movieVoteCount=itemView.findViewById(R.id.dtlVoteCountTV);
            movieOverview=itemView.findViewById(R.id.dtlOverviewTV);
            movieFullOverview=itemView.findViewById(R.id.dtlFullOverviewTV);
            movieFavourite=itemView.findViewById(R.id.dtlFavouriteIV);
            movieWatchLater=itemView.findViewById(R.id.dtlWatchLaterIV);
            movieReleaseStatus=itemView.findViewById(R.id.dtlReleaseStatusTV);
        }
    }

}
