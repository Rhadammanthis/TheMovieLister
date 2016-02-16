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
 * Created by Hugo on 2/15/2016.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolderSearch>{

    public ArrayList<MovieModel> dataSet;
    private Context context;

    public SearchAdapter(ArrayList<MovieModel> dataSet, Context context)
    {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolderSearch onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        SearchAdapter.ViewHolderSearch vH = new SearchAdapter.ViewHolderSearch(view);

        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolderSearch holder, int position) {

        holder.itemTitle.setText(dataSet.get(position).getTitle());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w300" +
                dataSet.get(position).getPosterPath()).into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolderSearch extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemTitle;
        public ImageView itemImage;

        public ViewHolderSearch(View v) {
            super(v);
            itemTitle = (TextView) v.findViewById(R.id.title_search);
            itemImage = (ImageView) v.findViewById(R.id.poster_search);

        }
    }
}
