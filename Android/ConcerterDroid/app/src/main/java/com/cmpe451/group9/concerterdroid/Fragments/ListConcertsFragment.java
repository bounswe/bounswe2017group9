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
import android.widget.Toast;

import com.cmpe451.group9.concerterdroid.Classes.Artist;
import com.cmpe451.group9.concerterdroid.Classes.Concert;
import com.cmpe451.group9.concerterdroid.Classes.Location;
import com.cmpe451.group9.concerterdroid.Adapters.MyConcertRecyclerViewAdapter;
import com.cmpe451.group9.concerterdroid.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListConcertsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    ArrayList<Concert> concertList = new ArrayList<Concert>();



    public ListConcertsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        //TODO according to given parameters and API search mechanisms, get a concert list and display the elements.(Fill this list with proper concert objects)
        concertList.add(new Concert("Deadpool Songs by DMX",new Artist("Ryan Reynolds"),new Location("X-Men Movie Theatre"),"Nov 21, 2017 8:30:53 PM",50));
        concertList.add(new Concert("American Horror Story Soundtrack",new Artist("Mirah"),new Location("Vodafone Arena"),"Oct 28, 2017 7:29:53 PM",100));
        concertList.add(new Concert("Back to Black",new Artist("Amy Winehouse"),new Location("Glastonbury"),"Jan 19, 2017 9:30:53 PM",150));
        concertList.add(new Concert("45. İstanbul Müzik Festivali",new Artist("Fazıl Say"),new Location("Küçük Çiftlik Park"),"Apr 10, 2017 9:00:53 PM",50));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concert_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyConcertRecyclerViewAdapter(concertList, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            Toast.makeText(getActivity(), "Clicked a list item, but it did not work properly", Toast.LENGTH_LONG).show();
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Concert item);
    }

}
