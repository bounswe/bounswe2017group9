package com.example.abtasdan.listviewtutorial.activities;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.modals.CommentObject;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;
import com.example.abtasdan.listviewtutorial.requests.requests.ConcertifyApiRequest;
import com.example.abtasdan.listviewtutorial.requests.requests.RetrofitHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class ConcertActivity extends AppCompatActivity {

    @BindView(R.id.iv_concertImage)
    ImageView ivConcertImage;
    @BindView(R.id.tv_concertName)
    TextView tvConcertName;
    @BindView(R.id.tv_artistName)
    TextView tvArtistName;
    @BindView(R.id.tv_concertLocation)
    TextView tvConcertLocation;
    @BindView(R.id.tv_concertPrice)
    TextView tvConcertPrice;
    @BindView(R.id.tv_concertDate)
    TextView tvConcertDate;
    @BindView(R.id.lv_comments)
    ListView lvComments;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.btn_comment)
    Button btnComment;

    private RestAdapter restAdapter;
    private ArrayList<String> comments;
    private ConcertifyApiRequest concertifyApiRequest;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert);
        comments = new ArrayList<String>();

        ButterKnife.bind(this);

        RetrofitHttpClient client = new RetrofitHttpClient();


        restAdapter = new RestAdapter.Builder()
                .

                        setClient(client)

                .

//                        setErrorHandler(new MyErrorHandler()
//
//                        )
//                .

//                        setRequestInterceptor(requestInterceptor)
//
//                .

//                        setConverter(new JacksonConverter()
//
//                        ).

//                        setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
//
//                .

        setLogLevel(RestAdapter.LogLevel.FULL)

                .

                        setLog(new AndroidLog("YOUR_LOG_TAG")

                        )
                .

                        setEndpoint(getString(R.string.base_url)

                        )
                .

                        build();


        concertifyApiRequest = restAdapter.create(ConcertifyApiRequest.class);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Concert concert_object = (Concert) getIntent().getSerializableExtra("concert_object");
        Glide.with(this).load(concert_object.getImage_path()).into(ivConcertImage);
        tvConcertName.setText(concert_object.getName());
        tvArtistName.setText(concert_object.getArtist().getName());
        tvConcertLocation.setText(concert_object.getLocation().getAddress());
        tvConcertPrice.setText(String.valueOf(concert_object.getMax_price()));
        tvConcertDate.setText(concert_object.getDate_time());
    }
    @OnClick(R.id.btn_comment)
    public void refresh() {

        refreshItems();
    }
    public void refreshItems(){
        String comment = etComment.getText().toString();

        if(comment.isEmpty() ){

        }else {
            comments.add(comment);



        }
    }
    /*
    public CommentObject getComments(){
        try {
            URL url = new URL(+"/recommend/"+user.getId()+";");
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            resultJson = br.readLine();
            resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
            for(Concerts oneConcert : resultList) {
                oneConcert.parseDate(oneConcert.getDate_time());
                ArrayList<Comments> commentList;
                Comments comment;
                url = new URL(Application.API_ENDPOINT+"/concerts/"+oneConcert.getId()+"/comments");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                resultJson = br.readLine();
                commentList = new ArrayList<Comments> (Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));
                oneConcert.setCommentList(commentList);


                ArrayList<SemanticTag> semanticTagList;
                url = new URL(Application.API_ENDPOINT+"/semantic-tags/"+oneConcert.getId());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                resultJson = br.readLine();
                semanticTagList = new ArrayList<SemanticTag> (Arrays.asList(Application.gson.fromJson(resultJson, SemanticTag[].class)));
                oneConcert.setSemanticTagList(semanticTagList);

            }
            return resultList;
        }catch(MalformedURLException ex) {
            ex.printStackTrace();
        }catch(IOException ex) {
            ex.printStackTrace();
        }

    }
    */
}
