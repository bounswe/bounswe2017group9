package com.example.abtasdan.listviewtutorial.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abtasdan on 27.12.2017.
 */
/*
public class CommentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Concert> comment;
 @Override
 public int getCount() {
     return (comment.size());
 }

    @Override
    public Object getItem(int position) {
        return comment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = mInflater.inflate(R.layout.row_concert, null);
        ConcertAdapter.ViewHolder holder = new ConcertAdapter.ViewHolder(rowView);

        final Concert concert = concert.get(position);
        fillConcerts(concert, holder);

        return rowView;
    }

    private void fillConcerts(Concert concert, ConcertAdapter.ViewHolder holder) {
        Glide.with(context).load(concert.getImage_path()).into(holder.ivImage);
        holder.tvArtist.setText(concert.getArtist().getName());

        if (preLogin) {
            holder.tvPrice.setVisibility(View.GONE);
        } else {
            holder.tvPrice.setText(concert.getMin_price()+"" );

        }
        holder.tvComments.setText(concert.getMax_price()*9/5+" Comments");
        holder.tvDate.setText(concert.getDate_time());
        holder.tvAttending.setText(concert.getMax_price()*7+" Attendings");
        holder.tvLocation.setText(concert.getLocation().getCity());

    }




    static class ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_artist)
        TextView tvArtist;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_comments)
        TextView tvComments;
        @BindView(R.id.tv_attending)
        TextView tvAttending;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



}
*/