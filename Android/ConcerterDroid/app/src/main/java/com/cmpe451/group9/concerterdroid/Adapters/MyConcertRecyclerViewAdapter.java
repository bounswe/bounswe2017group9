package com.cmpe451.group9.concerterdroid.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmpe451.group9.concerterdroid.Classes.Concert;
import com.cmpe451.group9.concerterdroid.Fragments.ListConcertsFragment.OnListFragmentInteractionListener;
import com.cmpe451.group9.concerterdroid.R;


import java.util.List;

public class MyConcertRecyclerViewAdapter extends RecyclerView.Adapter<MyConcertRecyclerViewAdapter.ViewHolder> {

    private final List<Concert> concert_list;
    private final OnListFragmentInteractionListener mListener;

    public MyConcertRecyclerViewAdapter(List<Concert> items, OnListFragmentInteractionListener listener) {
        this.concert_list = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_concert, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//
        if(concert_list.get(position) != null){
            holder.concert = concert_list.get(position);
            holder.tvName.setText("" + holder.concert.getName());
            holder.ivImage.setImageResource(R.drawable.ic_placeholder);
            holder.tvArtist.setText(holder.concert.getArtist().getName());
            holder.tvLocation.setText(holder.concert.getLocation().getAddress());
//            holder.tvPrice.setText("" + holder.concert.getPriceRange());
            String[] words = holder.concert.getDateTime().split(" ");
            holder.tvDateMonth.setText(words[0]);
            holder.tvDateDay.setText(words[1].substring(0,words[1].length()-1));
//            holder.tvTime.setText(words[3].substring(0,words[3].length()-3) + " " + words[4]);
            holder.tvRate.setText("4.5 / 5");// Temporary
            holder.tvAttending.setText("35");// Temporary
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.concert);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return concert_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView tvName;
        public final ImageView ivImage;
        public final TextView tvArtist;
        public final TextView tvLocation;
//        public final TextView tvPrice;
        public final TextView tvDateMonth;
        public final TextView tvDateDay;
//        public final TextView tvTime;
        public final TextView tvAttending;
        public final TextView tvRate;
        public Concert concert;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvName = (TextView) view.findViewById(R.id.tv_name);
            ivImage = (ImageView) view.findViewById(R.id.iv_image);
            tvArtist = (TextView) view.findViewById(R.id.tv_artist);
            tvLocation = (TextView) view.findViewById(R.id.tv_location);
//            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            tvDateMonth = (TextView) view.findViewById(R.id.tv_date_month);
            tvDateDay = (TextView) view.findViewById(R.id.tv_date_day);
//            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvRate = (TextView) view.findViewById(R.id.tv_rate);
            tvAttending = (TextView) view.findViewById(R.id.tv_attending);
        }

    }
}
