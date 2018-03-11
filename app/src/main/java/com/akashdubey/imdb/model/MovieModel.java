package com.akashdubey.imdb.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by homepc on 07-03-2018.
 */

public class MovieModel {

    private String mTitle,mReleaseDate,mPopularity,mVotesCount,mMovieImage,mVoteAverage,mId;

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public MovieModel(String mTitle, String mReleaseDate, String mPopularity, String mVotesCount, String mMovieImage, String mVoteAverage, String mId) {
        this.mTitle = mTitle;
        this.mReleaseDate = mReleaseDate;
        this.mPopularity = mPopularity;
        this.mVotesCount = mVotesCount;
        this.mMovieImage = mMovieImage;
        this.mVoteAverage=mVoteAverage;
        this.mId=mId;

    }



    public  static List<MovieModel> movieModelList = new ArrayList<>();

    public   String getmTitle() {
        return mTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmPopularity() {
        return mPopularity;
    }

    public String getmVotesCount() {
        return mVotesCount;
    }

    public String getmMovieImage() {
        return mMovieImage;
    }

    public String getmId() {  return mId;    }

    public static List<MovieModel> getMovieModel() {
        return movieModelList;
    }
}
