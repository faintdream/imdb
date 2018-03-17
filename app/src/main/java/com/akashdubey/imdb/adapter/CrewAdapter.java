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

import java.util.List;

/**
 * Created by homepc on 13-03-2018.
 */

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewHolder> {

    List<MovieDetailsModel> crewAdapterList;
    public CrewAdapter(List<MovieDetailsModel> crewModelList) {
        this.crewAdapterList=crewModelList;
    }

    @Override
    public CrewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crew_view,parent,false);
        return new CrewHolder(view);
    }

    @Override
    public void onBindViewHolder(CrewHolder holder, int position) {
        MovieDetailsModel movieDetailsModel=crewAdapterList.get(position);
        if(movieDetailsModel.getmCrewImage().equals("http://image.tmdb.org/t/p/w45null")){
            holder.crewImage.setImageResource(R.drawable.no_image);
        }else {
            Glide.with(holder.crewImage.getContext())
                    .load(movieDetailsModel.getmCrewImage())
                    .into(holder.crewImage);
        }
        holder.crewTitle.setText(movieDetailsModel.getmCrewTitle());
    }

    @Override
    public int getItemCount() {
        return crewAdapterList.size();
    }

    public class CrewHolder extends RecyclerView.ViewHolder{
        ImageView crewImage;
        TextView crewTitle;
        public CrewHolder(View itemView) {
            super(itemView);
            crewImage=itemView.findViewById(R.id.dtlCrewIV);
            crewTitle=itemView.findViewById(R.id.dtlCrewTV);
        }
    }
}
