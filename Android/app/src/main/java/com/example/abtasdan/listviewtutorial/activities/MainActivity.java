package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.abtasdan.listviewtutorial.Concert;
import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.adapters.ConcertAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.lv_concerts)
    ListView lvConcerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<Concert> concerts = new ArrayList<>();
        concerts.add(new Concert("Buğra","","12","","","https://vikolo.com/wp-content/uploads/2017/03/Lana-Del-Rey.jpg",12,13));
        concerts.add(new Concert("Serdar","","13","","","https://vikolo.com/wp-content/uploads/2017/03/Lana-Del-Rey.jpg",12,13));
        concerts.add(new Concert("Hazel","","14","","","https://vikolo.com/wp-content/uploads/2017/03/Lana-Del-Rey.jpg",12,13));

        ConcertAdapter concertAdapter = new ConcertAdapter(this,concerts,false);
        lvConcerts.setAdapter(concertAdapter);

    }
}
