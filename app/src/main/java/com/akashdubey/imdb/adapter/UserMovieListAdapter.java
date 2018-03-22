package com.akashdubey.imdb.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.akashdubey.imdb.MainActivity;
import com.akashdubey.imdb.R;
import com.bumptech.glide.Glide;

import static com.akashdubey.imdb.db.Constants.*;

/**
 * This class uses sqlite db to populate the User chosen Movie list in REcyclerview
 */

public class UserMovieListAdapter extends RecyclerView.Adapter<UserMovieListAdapter.MyHolder>  {
    private Cursor umlCursor;

    public UserMovieListAdapter(Cursor umlCursor) {
        this.umlCursor = umlCursor;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_movie_list_adapter_view,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
            umlCursor.moveToPosition(position);
            Glide.with(holder.movieImage.getContext()).load(umlCursor.getString(umlCursor.getColumnIndex(POSTER_PATH))).into(holder.movieImage);
            holder.movieTitle.setText(umlCursor.getString(umlCursor.getColumnIndex(TITLE)));
            holder.releaseDate.setText(umlCursor.getString(umlCursor.getColumnIndex(RELEASE_DATE)));
            holder.votesCount.setText(
                    umlCursor.getString(umlCursor.getColumnIndex(VOTE_AVERAGE))+
                            "/10 by " +
                    umlCursor.getString(umlCursor.getColumnIndex(VOTE_COUNT))+
            " Users.");
            holder.ratings.setRating(Float.parseFloat(umlCursor.getString(umlCursor.getColumnIndex(VOTE_AVERAGE)))/2);
    }

    @Override
    public int getItemCount() {
        return umlCursor.getCount();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        RatingBar ratings;
        ImageView movieImage;
        TextView movieTitle, releaseDate, popularity, votesCount;
        public MyHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.umlPosterIV);
            movieTitle = itemView.findViewById(R.id.umlMovieNameTV);
            releaseDate = itemView.findViewById(R.id.umlReleasedateTV);
            votesCount = itemView.findViewById(R.id.umlVotesTV);
            ratings = itemView.findViewById(R.id.umlRatingsRB);
        }
    }


}
