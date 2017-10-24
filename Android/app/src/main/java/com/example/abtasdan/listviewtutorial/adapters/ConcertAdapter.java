package com.example.abtasdan.listviewtutorial.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abtasdan.listviewtutorial.Concert;
import com.example.abtasdan.listviewtutorial.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abtasdan on 24.10.2017.
 */

public class ConcertAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<Concert> concerts;
    Context context;
    boolean preLogin;

    public ConcertAdapter(Activity activity, List<Concert> concerts, boolean preLogin) {

        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        this.concerts = concerts;
        this.preLogin = preLogin;
        context = activity;
    }

    @Override
    public int getCount() {
        return concerts.size();
    }

    @Override
    public Object getItem(int position) {
        return concerts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = mInflater.inflate(R.layout.row_concert, null);
        ViewHolder holder = new ViewHolder(rowView);

        final Concert concert = concerts.get(position);
        fillViews(concert, holder);


        return rowView;
    }

    private void fillViews(Concert concert, ViewHolder holder) {
        Glide.with(context).load(concert.getImageUrl()).into(holder.ivImage);
        holder.tvArtist.setText("Artist: " + concert.getArtist());

        if (preLogin) {
            holder.tvPrice.setVisibility(View.GONE);
        }else{
            holder.tvPrice.setText("Price: " + concert.getPrice());

        }

    }


    static class ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_artist)
        TextView tvArtist;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
