package com.akashdubey.imdb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homepc on 13-03-2018.
 */

public class PosterModel {

    String posterImage;
    public static List<PosterModel> posterModelList= new ArrayList<>();

    public static List<PosterModel> getPosterModelList() {
        return posterModelList;
    }

    public PosterModel(String posterImage) {
        this.posterImage = posterImage;
    }


}
