package me.hugomedina.themovielister.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.models.MovieModel;

/**
 * Created by Hugo on 3/31/2016.
 */
public class CustomGridAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int HEADER_TYPE = 0;
    private int DEFAULT_TYPE = 1;

    public ArrayList<MovieModel> dataSet;
    private Context context;

    public CustomGridAdapter(ArrayList<MovieModel> dataSet, Context context)
    {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == HEADER_TYPE)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_custom_grid_header, parent, false);
            CustomGridAdapter.ViewHolderHeader vH = new CustomGridAdapter.ViewHolderHeader(view);

            return vH;
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_custom_grid, parent, false);
            CustomGridAdapter.ViewHolder vH = new CustomGridAdapter.ViewHolder(view);

            return vH;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof CustomGridAdapter.ViewHolderHeader)
        {
            CustomGridAdapter.ViewHolderHeader vHHeader = (CustomGridAdapter.ViewHolderHeader) holder;

            vHHeader.title.setText(dataSet.get(position).getTitle());
            Picasso.with(context).load("https://image.tmdb.org/t/p/w300" +
                    dataSet.get(position).getPosterPath()).into(vHHeader.image);
        }
        else
        {
            CustomGridAdapter.ViewHolder vHDefault = (CustomGridAdapter.ViewHolder) holder;

            vHDefault.title.setText(dataSet.get(position).getTitle());
            Picasso.with(context).load("https://image.tmdb.org/t/p/w300" +
                    dataSet.get(position).getPosterPath()).into(vHDefault.image);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position==0 || position == 4)
            return DEFAULT_TYPE;
        else
            return DEFAULT_TYPE;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private class ViewHolderHeader extends RecyclerView.ViewHolder
    {

        public ImageView image;
        public TextView title;

        public ViewHolderHeader(View v)
        {
            super(v);
            image = (ImageView) v.findViewById(R.id.item_grid_image);
            title = (TextView) v.findViewById(R.id.item_grid_title);
        }

    }

    private class ViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView image;
        public TextView title;

        public ViewHolder(View v)
        {
            super(v);
            image = (ImageView) v.findViewById(R.id.item_grid_image);
            title = (TextView) v.findViewById(R.id.item_grid_title);
        }

    }
}
