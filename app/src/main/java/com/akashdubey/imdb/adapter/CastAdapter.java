package com.akashdubey.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akashdubey.imdb.R;
import com.akashdubey.imdb.model.MovieDetailsModel;
import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.akashdubey.imdb.DetailsScreen.castRV;

/**
 * Created by homepc on 13-03-2018.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {

    List<MovieDetailsModel> castAdapterList = new ArrayList<>();

    public CastAdapter(List<MovieDetailsModel> castModelList) {
        this.castAdapterList = castModelList;
    }

    @Override
    public CastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_view, parent, false);
        return new CastHolder(view);
    }

    @Override
    public void onBindViewHolder(CastHolder holder, int position) {
        MovieDetailsModel movieDetailsModel = castAdapterList.get(position);

        //if there is no image fetched, let's replace it by a custom image
        if (movieDetailsModel.getmCastImage().equals("http://image.tmdb.org/t/p/w45null")) {
            holder.castImage.setImageResource(R.drawable.no_image);
        } else {
            Glide.with(holder.castImage.getContext())
                    .load(movieDetailsModel.getmCastImage())
                    .into(holder.castImage);

        }
        holder.castTitle.setText(movieDetailsModel.getmCastTitle());

    }

    @Override
    public int getItemCount() {

        return castAdapterList.size();
    }

    public class CastHolder extends RecyclerView.ViewHolder {
        ImageView castImage;
        TextView castTitle;

        public CastHolder(View itemView) {
            super(itemView);
            castImage = itemView.findViewById(R.id.dtlCastIV);
            castTitle = itemView.findViewById(R.id.dtlCastTV);
        }
    }
}
