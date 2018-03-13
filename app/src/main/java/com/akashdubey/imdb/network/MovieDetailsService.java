package com.akashdubey.imdb.network;

/**
 * Created by homepc on 13-03-2018.
 */

public class MovieDetailsService {

    private String movieId="TBD";
    private String MOVIE_DETAIL_URL
            ="http://api.themoviedb.org/3/movie/"+movieId+"?api_key=8496be0b2149805afa458ab8ec27560c";
    private String POSTER_URL
            ="http://api.themoviedb.org/3/movie/"+movieId+"/images?api_key=8496be0b2149805afa458ab8ec27560c";
    private String CAST_CREW_URL
            ="http://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key=8496be0b2149805afa458ab8ec27560c";
    private String TRAILER_URL
            ="http://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key=8496be0b2149805afa458ab8ec27560c";

    private String DYNAMIC_IMG="TBD.jpeg";
    private String IMG_URL=
            "http://image.tmdb.org/t/p/w500/"+DYNAMIC_IMG;



}
