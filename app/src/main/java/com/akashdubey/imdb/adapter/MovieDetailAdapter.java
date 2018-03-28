package com.akashdubey.imdb.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akashdubey.imdb.MainActivity.*;
import com.akashdubey.imdb.R;
import com.akashdubey.imdb.model.MovieDetailsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.akashdubey.imdb.db.Constants.*;
import static com.akashdubey.imdb.db.DbHelper.dbHelper;
import static com.akashdubey.imdb.db.DbHelper.sqLiteDatabase;
import static com.akashdubey.imdb.network.MovieDetailsService.movieId;

/**
 * Created by homepc on 13-03-2018.
 */

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailHolder> {
    long result;
    private Context context;
    Cursor cursor;
    List<MovieDetailsModel> movieDetailAdapterList = new ArrayList<>();

    public MovieDetailAdapter(List<MovieDetailsModel> movieDetailsModels) {
        this.movieDetailAdapterList = movieDetailsModels;
    }

    @Override
    public MovieDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_detail_view, parent, false);
        context = parent.getContext();
        return new MovieDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieDetailHolder holder, final int position) {

        final MovieDetailsModel movieDetailsModel = movieDetailAdapterList.get(position);
        String movieTitle = movieDetailsModel.getmTitle().toString();
        Integer length;
        if (movieTitle.length() > 100) {
            length = 100;
        } else {
            length = 50;
        }
        Glide.with(holder.movieImage.getContext()).load(movieDetailsModel.getmMovieImage()).into(holder.movieImage);
        holder.movieTitle.setText(movieDetailsModel.getmTitle());
        holder.rating.setRating(Float.parseFloat(movieDetailsModel.getmVoteCount()));
        holder.movieOverview.setText(movieDetailsModel.getmOverview().substring(0, length) + "...");
        holder.movieFullOverview.setText(movieDetailsModel.getmOverview());
        holder.movieVoteAverage.setText("(" + movieDetailsModel.getmVoteAverage() + "/10)");
        holder.movieVoteCount.setText(movieDetailsModel.getmVoteCount() + " users");
        holder.movieReleaseDate.setText(movieDetailsModel.getmReleaseDate());
        holder.movieBudget.setText("Budget: " + movieDetailsModel.getmBudget());
        holder.movieRevenue.setText("Revenue: " + movieDetailsModel.getmRevenue());
        holder.movieReleaseStatus.setText(movieDetailsModel.getmReleaseStatus());
        holder.movieFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.openConnection();
                String[] args = {movieId};
                cursor = sqLiteDatabase.query(TABLE_NAME,
                        new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
                                VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, ID + "=?"
                        , args, null, null, null);
                ContentValues cv = new ContentValues();
                if (cursor.getCount() < 1) {
                    cv.put(ID, movieId);
                    cv.put(TITLE, movieDetailsModel.getmTitle());
                    cv.put(RELEASE_DATE, movieDetailsModel.getmReleaseDate());
                    cv.put(POSTER_PATH, movieDetailsModel.getmMovieImage());
                    cv.put(POPULARITY, movieDetailsModel.getmVoteCount());
                    cv.put(VOTE_AVERAGE, movieDetailsModel.getmVoteAverage());
                    cv.put(VOTE_COUNT, movieDetailsModel.getmVoteCount());
                    cv.put(IS_FAVOURITE, "yes");
                    cv.put(IS_WATCHLIST, "no");
                    sqLiteDatabase.insert(TABLE_NAME, null, cv);
                    holder.movieFavourite.setImageResource(R.drawable.favorite_enable);
                    cursor.close();
                    dbHelper.closeConnection();
                } else {

                    while (cursor.moveToNext()) {
                        String tmpWatchList = cursor.getString(cursor.getColumnIndex(IS_WATCHLIST));
                        String tmpfavourite = cursor.getString(cursor.getColumnIndex(IS_FAVOURITE));
                        if (tmpfavourite.equals("no") && tmpWatchList.equals("yes")) {
                            String[] args2 = {"no", movieId};
                            cv.put(IS_FAVOURITE, "yes");
                            sqLiteDatabase.update(TABLE_NAME, cv, IS_FAVOURITE + "=?" + " AND " + ID + "=?", args2);
                            holder.movieFavourite.setImageResource(R.drawable.favorite_enable);
                        } else {
                            Toast.makeText(holder.movieFavourite.getContext(),
                                    "It is already in favourite list", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

//                Log.i("LEGO", "onBindViewHolder -> MovieName : " + movieDetailsModel.getmTitle());
//                Log.i("LEGO", "onBindViewHolder -> MoviePoster : " + movieDetailsModel.getmMovieImage());

//                dbHelper.openConnection();
//                String[] args = {movieId, "no"};
//                cursor = sqLiteDatabase.query(TABLE_NAME,
//                        new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
//                                VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, ID + "=?" + "AND " + IS_FAVOURITE + "=?"
//                        , args, null, null, null);
//                ContentValues cv = new ContentValues();
//                if (cursor.getCount() < 1) {
//                    cv.put(ID, movieId);
//                    cv.put(TITLE, movieDetailsModel.getmTitle());
//                    cv.put(RELEASE_DATE, movieDetailsModel.getmReleaseDate());
//                    cv.put(POSTER_PATH, movieDetailsModel.getmMovieImage());
//                    cv.put(POPULARITY, movieDetailsModel.getmVoteCount());
//                    cv.put(VOTE_AVERAGE, movieDetailsModel.getmVoteAverage());
//                    cv.put(VOTE_COUNT, movieDetailsModel.getmVoteCount());
//                    cv.put(IS_FAVOURITE, "yes");
//                    cv.put(IS_WATCHLIST, "no");
//                    sqLiteDatabase.insert(TABLE_NAME, null, cv);
//                    holder.movieFavourite.setImageResource(R.drawable.favorite_enable);
////                    cursor.close();
//                    dbHelper.closeConnection();
//                } else {
//
//                    Toast.makeText(holder.movieFavourite.getContext(),
//                            "It is already in favourite list", Toast.LENGTH_SHORT).show();
//
//                }
//
//
            }
        });

        holder.movieWatchLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.openConnection();
                String[] args = {movieId};
                cursor = sqLiteDatabase.query(TABLE_NAME,
                        new String[]{ID, TITLE, RELEASE_DATE, POSTER_PATH, POPULARITY, VOTE_AVERAGE,
                                VOTE_COUNT, IS_FAVOURITE, IS_WATCHLIST}, ID + "=?"
                        , args, null, null, null);
                ContentValues cv = new ContentValues();


                if (cursor.getCount() < 1) {
                    cv.put(ID, movieId);
                    cv.put(TITLE, movieDetailsModel.getmTitle());
                    cv.put(RELEASE_DATE, movieDetailsModel.getmReleaseDate());
                    cv.put(POSTER_PATH, movieDetailsModel.getmMovieImage());
                    cv.put(POPULARITY, movieDetailsModel.getmVoteCount());
                    cv.put(VOTE_AVERAGE, movieDetailsModel.getmVoteAverage());
                    cv.put(VOTE_COUNT, movieDetailsModel.getmVoteCount());
                    cv.put(IS_FAVOURITE, "no");
                    cv.put(IS_WATCHLIST, "yes");
                    sqLiteDatabase.insert(TABLE_NAME, null, cv);
                    holder.movieWatchLater.setImageResource(R.drawable.watchlist_enable);
                    cursor.close();
                    dbHelper.closeConnection();
                } else {
//                    if (cursor.getString(cursor.getColumnIndexOrThrow(IS_FAVOURITE)).equals("yes") && cursor.getString(cursor.getColumnIndexOrThrow(IS_WATCHLIST))) {

                    while (cursor.moveToNext()) {
                        String tmpWatchList = cursor.getString(cursor.getColumnIndex(IS_WATCHLIST));
                        String tmpfavourite = cursor.getString(cursor.getColumnIndex(IS_FAVOURITE)).toString();
                        if (tmpfavourite.equals("yes") && tmpWatchList.equals("no")) {
                            String[] args2 = {"no", movieId};
                            cv.put(IS_WATCHLIST, "yes");
                            sqLiteDatabase.update(TABLE_NAME, cv, IS_WATCHLIST + "=?" + " AND " + ID + "=?", args2);
                            holder.movieWatchLater.setImageResource(R.drawable.watchlist_enable);
                        } else {
                            Toast.makeText(holder.movieWatchLater.getContext(),
                                    "It is already in watch list", Toast.LENGTH_SHORT).show();


                        }
                    }

                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return movieDetailAdapterList.size();
    }

    public class MovieDetailHolder extends RecyclerView.ViewHolder {
        ImageView movieImage, movieFavourite, movieWatchLater;
        RatingBar rating;
        TextView movieTitle, movieOverview, movieReleaseDate, movieBudget, movieRevenue,
                movieFullOverview, movieVoteCount, movieVoteAverage, movieReleaseStatus;

        public MovieDetailHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.dtlPosterIV);
            movieTitle = itemView.findViewById(R.id.dtlMovieTitleTV);
            movieReleaseDate = itemView.findViewById(R.id.dtlReleaseDateTV);
            movieBudget = itemView.findViewById(R.id.dtlBudgetTV);
            movieRevenue = itemView.findViewById(R.id.dtlRevenueTV);
            rating = itemView.findViewById(R.id.dtlRatingsRB);
            movieVoteAverage = itemView.findViewById(R.id.dtlVoteAverageTV);
            movieVoteCount = itemView.findViewById(R.id.dtlVoteCountTV);
            movieOverview = itemView.findViewById(R.id.dtlOverviewTV);
            movieFullOverview = itemView.findViewById(R.id.dtlFullOverviewTV);
            movieFavourite = itemView.findViewById(R.id.dtlFavouriteIV);
            movieWatchLater = itemView.findViewById(R.id.dtlWatchLaterIV);
            movieReleaseStatus = itemView.findViewById(R.id.dtlReleaseStatusTV);
        }
    }

}
