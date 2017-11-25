package com.cmpe451.group9.concerterdroid.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmpe451.group9.concerterdroid.Classes.Comment;
import com.cmpe451.group9.concerterdroid.R;
//import com.cmpe451.group9.concerterdroid.Fragments.ListCommentFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyCommentRecyclerViewAdapter extends RecyclerView.Adapter<MyCommentRecyclerViewAdapter.ViewHolder> {

    private final List<Comment> comment_list;
//    private final OnListFragmentInteractionListener mListener;

    public MyCommentRecyclerViewAdapter(List<Comment> items) {
        comment_list = items;
//        mListener = listener;   -   , OnListFragmentInteractionListener listener
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(comment_list.get(position) != null) {
            holder.comment = comment_list.get(position);
            holder.ivOwner.setImageResource(R.drawable.user);// TODO After achieving image upload, fill this part with the one from the API.
            holder.tvOwner.setText("Rudolph Sweden");// TODO Using API get the user/owner name. (The user which is owner of the comment.)
            holder.tvContent.setText(holder.comment.getContent());
//            holder.ivOwner.setImageResource(R.drawable.ic_placeholder);

        }


//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return comment_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvOwner;
        public final ImageView ivOwner;
        public final TextView tvContent;
        public Comment comment;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivOwner = (ImageView) view.findViewById(R.id.iv_owner);
            tvOwner = (TextView) view.findViewById(R.id.tv_owner);
            tvContent = (TextView) view.findViewById(R.id.tv_content);
        }
    }
}
