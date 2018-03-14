package com.akashdubey.imdb.model;

import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles mode for detailed Movie list and associated methods
 */

public class MovieDetailsModel {
    private String mTitle;
    private String mReleaseDate;
    private String mPosterImage;
    private String mVoteAverage;
    private String mOverview;
    private String mBudget;
    private String mRevenue;
    private String mTrailerImage;
    private String mCastImage;
    private String mCrewImage;
    private String mVoteCount;
    private boolean isFavourite,isWatchLater;
    private RatingBar ratings;

    public static List<MovieDetailsModel> movieDetailsModelList = new ArrayList<>();
    public static List<MovieDetailsModel> posterModelList= new ArrayList<>();



    public MovieDetailsModel(String mTitle, String mReleaseDate, String mVoteAverage, String mOverview, String mBudget, String mRevenue, String mVoteCount) {
        this.mTitle = mTitle;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
        this.mOverview = mOverview;
        this.mBudget = mBudget;
        this.mRevenue = mRevenue;
        this.mVoteCount = mVoteCount;
    }

    public static List<MovieDetailsModel> getMovieDetailsModelList() {
        return movieDetailsModelList;
    }


    public MovieDetailsModel(String mPosterImage) {
        this.mPosterImage = mPosterImage;
    }

    public static List<MovieDetailsModel> getPosterModelList() {
        return posterModelList;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmTrailerImage() {
        return mTrailerImage;
    }

    public String getmCastImage() {
        return mCastImage;
    }

    public String getmVoteCount() {
        return mVoteCount;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
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

    public String getmCrewImage() {
        return mCrewImage;
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
