package com.akashdubey.imdb.model;

import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles mode for detailed Movie list and associated methods
 */

public class MovieDetailsModel {
    private String mTitle;
    private  String mMovieImage;
    private String mReleaseDate;
    private String mPosterImage;
    private String mVoteAverage;
    private String mOverview;
    private String mBudget;
    private String mRevenue;
    private String mTrailerKey;
    private String mTrailerName;
    private String mCastImage;
    private String mCastTitle;
    private String mCrewImage;
    private String mCrewTitle;
    private String mVoteCount;
    private String mReleaseStatus;
    private String dummy1,dummy2;
    private boolean isFavourite,isWatchLater;
    private RatingBar ratings;

    public static List<MovieDetailsModel> movieDetailsModelList = new ArrayList<>();
    public static List<MovieDetailsModel> posterModelList= new ArrayList<>();
    public static List<MovieDetailsModel> trailerModelList=new ArrayList<>();
    public static List<MovieDetailsModel> castModelList= new ArrayList<>();
    public static List<MovieDetailsModel>crewModelList=new ArrayList<>();




    public MovieDetailsModel(String dummy1,String mCastImage, String mCastTitle) {
        this.mCastImage = mCastImage;
        this.mCastTitle = mCastTitle;
    }

    public MovieDetailsModel(String dummy2,String dummy1,String mCrewImage, String mCrewTitle) {
        this.mCrewImage = mCrewImage;
        this.mCrewTitle = mCrewTitle;
    }

    public MovieDetailsModel(String mTrailerKey, String mTrailerName) {
        this.mTrailerKey = mTrailerKey;
        this.mTrailerName = mTrailerName;
    }


    public String getmCastTitle() {
        return mCastTitle;
    }

    public String getmCrewTitle() {
        return mCrewTitle;
    }

    public MovieDetailsModel(String mMovieImage, String mTitle, String mReleaseDate, String mVoteAverage, String mOverview, String mBudget, String mRevenue, String mVoteCount, String mReleaseStatus) {
        this.mMovieImage=mMovieImage;
        this.mTitle = mTitle;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
        this.mOverview = mOverview;
        this.mBudget = mBudget;
        this.mRevenue = mRevenue;
        this.mVoteCount = mVoteCount;
        this.mReleaseStatus=mReleaseStatus;
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

    public String getmTrailerKey() {
        return mTrailerKey;
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

    public String getmMovieImage() {
        return mMovieImage;
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

    public String getmTrailerName() {
        return mTrailerName;
    }

    public String getmReleaseStatus() {
        return mReleaseStatus;
    }
}
