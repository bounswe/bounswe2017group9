package com.cmpe451.group9.concerterdroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe451.group9.concerterdroid.Classes.Comment;
import com.cmpe451.group9.concerterdroid.Adapters.MyCommentRecyclerViewAdapter;
import com.cmpe451.group9.concerterdroid.R;

import java.util.ArrayList;


public class ListCommentFragment extends Fragment {

    ArrayList<Comment> commentList = new ArrayList<Comment>();


    public ListCommentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO Get the list of comments (from API) related to the concert page which this fragment is used.
        commentList.add(new Comment("Love you. It was a great concert and the ambiance was also very very nice. Loved the lighting and the sound system. Good job from the concert staff."));
        commentList.add(new Comment("Love your songs."));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyCommentRecyclerViewAdapter(commentList, getActivity()));
        }
        return view;
    }

}
