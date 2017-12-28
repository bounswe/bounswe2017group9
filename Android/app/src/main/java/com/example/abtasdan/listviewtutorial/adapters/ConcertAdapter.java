package com.example.abtasdan.listviewtutorial.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.activities.ConcertActivity;
import com.example.abtasdan.listviewtutorial.activities.UserActivity;
import com.example.abtasdan.listviewtutorial.modals.ArtistObject;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abtasdan on 24.10.2017.
 */

public class ConcertAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<Concert> concerts;
    private List<CreatedBy> creatorList;
    Context context;
    boolean preLogin;

    public ConcertAdapter(Activity activity, List<Concert> concerts, List<CreatedBy> creatorList, boolean preLogin) {

        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        this.concerts = concerts;
        this.preLogin = preLogin;
        this.creatorList = creatorList;
        context = activity;
    }

    @Override
    public int getCount() {
        return (concerts == null ? 0 : concerts.size() )+ (creatorList == null ? 0 :creatorList.size());
    }

    @Override
    public Object getItem(int position) {
        if(position < concerts.size()){
            return concerts.get(position);
        } else {
            return creatorList.get(position - creatorList.size());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        if(position < (concerts == null ? 0: concerts.size())) {
            rowView = mInflater.inflate(R.layout.row_concert, null);
            ViewHolder holder = new ViewHolder(rowView);

            final Concert concert = concerts.get(position);
            fillConcerts(concert, holder);
        }else {
            rowView = mInflater.inflate(R.layout.row_creator, null);
            CreatorViewHolder holder = new CreatorViewHolder(rowView);

            final CreatedBy creator = creatorList.get(position - creatorList.size());
            fillCreators(creator, holder);
        }
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position < concerts.size()){
                    Intent intent = new Intent(context , ConcertActivity.class);
                    intent.putExtra("concert_object",concerts.get(position));
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context , UserActivity.class);
                    intent.putExtra("user_object",creatorList.get(position- concerts.size()));
                    context.startActivity(intent);
                }
            }
        });

        return rowView;
    }

    private void fillConcerts(Concert concert, ViewHolder holder) {
        Glide.with(context).load(concert.getImage_path()).into(holder.ivImage);
        holder.tvArtist.setText(concert.getArtist().getName());

        if (preLogin) {
            holder.tvPrice.setVisibility(View.GONE);
        } else {
            holder.tvPrice.setText(concert.getMin_price()+"" );

        }
        holder.tvComments.setText("Comments");
        holder.tvDate.setText(concert.getDate_time());
        holder.tvAttending.setText("0 Attendings");
        holder.tvLocation.setText(concert.getLocation().getCity());

    }

    private void fillCreators(CreatedBy creator,CreatorViewHolder holder){
        Glide.with(context).load(creator.getPhoto_path()).into(holder.ivUserImage);
        holder.tvUserName.setText(creator.getName());


        holder.tvUserFollower.setText(""+creator.getFollowers());
        holder.tvUserFollowing.setText(""+creator.getFollowings());

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

    static class CreatorViewHolder{
        @BindView(R.id.iv_userImage)
        ImageView ivUserImage;
        @BindView(R.id.tv_userName)
        TextView tvUserName;
        @BindView(R.id.tv_user_follower)
        TextView tvUserFollower;
        @BindView(R.id.tv_user_following)
        TextView tvUserFollowing;


        CreatorViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
