package com.akashdubey.imdb.model;

import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles mode for detailed Movie list and associated methods
 */

public class MovieDetailsModel {
    private String mTitle,mReleaseDate,mPopularity,mFavouriteUsers,
            mPosterImage,mVoteAverage,mOverview,mBudget,mRevenue,
            mTrailer,mCast,mCrew;
    private boolean isFavourite,isWatchLater;
    private RatingBar ratings;
    private static List<MovieDetailsModel> movieDetailsModelList = new ArrayList<>();

    public static List<MovieDetailsModel> getMovieDetailsModelList() {
        return movieDetailsModelList;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmPopularity() {
        return mPopularity;
    }

    public String getmFavouriteUsers() {
        return mFavouriteUsers;
    }

    public String getmPosterImage() {
        return mPosterImage;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmOverview() {
        return mOverview;
    }

    public String getmBudget() {
        return mBudget;
    }

    public String getmRevenue() {
        return mRevenue;
    }

    public String getmTrailer() {
        return mTrailer;
    }

    public String getmCast() {
        return mCast;
    }

    public String getmCrew() {
        return mCrew;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public boolean isWatchLater() {
        return isWatchLater;
    }

    public RatingBar getRatings() {
        return ratings;
    }
}
