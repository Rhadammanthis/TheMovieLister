package me.hugomedina.themovielister.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.interfaces.OnItemClicked;
import me.hugomedina.themovielister.objects.models.Cast;
import me.hugomedina.themovielister.objects.models.MovieModel;

/**
 * Created by Hugo on 2/15/2016.
 */
public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder>{

    public ArrayList<Cast> dataSet;
    private Context context;
    private OnItemClicked clickListener;

    public CastAdapter(ArrayList<Cast> dataSet, Context context)
    {
        this.dataSet = dataSet;
        this.context = context;
        //this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cast, parent, false);
        CastAdapter.ViewHolder vH = new CastAdapter.ViewHolder(view);

        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.castName.setText(dataSet.get(position).getActor());
        holder.castCharacter.setText(dataSet.get(position).getCharacter());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w300" +
                dataSet.get(position).getPicturePath()).into(holder.castPic);

    }

    @Override
    public int getItemCount() {
        if(dataSet.size() > 5)
            return 5;
        else
            return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView castName, castCharacter;
        public ImageView castPic;

        public ViewHolder(View v) {
            super(v);
            castName = (TextView) v.findViewById(R.id.cast_name);
            castCharacter = (TextView) v.findViewById(R.id.cast_character);
            castPic = (ImageView) v.findViewById(R.id.cast_picture);

        }
    }
}
