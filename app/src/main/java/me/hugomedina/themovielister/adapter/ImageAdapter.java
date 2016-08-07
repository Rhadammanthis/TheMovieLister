package me.hugomedina.themovielister.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.interfaces.OnItemClicked;
import me.hugomedina.themovielister.objects.models.ImageModel;

/**
 * Created by Hugo on 2/15/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    public ArrayList<ImageModel> dataSet;
    private Context context;
    private OnItemClicked clickListener;

    public ImageAdapter(ArrayList<ImageModel> dataSet, Context context)
    {
        this.dataSet = dataSet;
        this.context = context;
        //this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        ImageAdapter.ViewHolder vH = new ImageAdapter.ViewHolder(view);

        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" +
                dataSet.get(position).getFile_path()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        if(dataSet.size() > 10)
            return 10;
        else
            return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.item_image_image);

        }
    }
}
