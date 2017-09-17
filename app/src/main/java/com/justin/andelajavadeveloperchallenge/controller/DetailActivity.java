package com.justin.andelajavadeveloperchallenge.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.justin.andelajavadeveloperchallenge.R;


public class DetailActivity extends AppCompatActivity {
    TextView Link, Username;
    Toolbar actionBarToolBar;
    ImageView imageView;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_detail);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView =(ImageView) findViewById(R.id.user_image_header);
        Username = (TextView) findViewById(R.id.username);
        Link= (TextView) findViewById(R.id.githublink);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = getIntent().getExtras().getString("login");
                String Link = getIntent().getExtras().getString("html_url");
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer @" + Username + ", " + Link);
                startActivity(Intent.createChooser(shareIntent, "From Gconnect"
                ));
            }

        });



        String username = getIntent().getExtras().getString("login");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarUrl)
               /* .placeholder(R.drawable.ic_account_circle_black_24dp)*/
                .into(imageView);
        getSupportActionBar().setTitle("Details Activity");

    }

    private Intent createShareForecastIntent(){
        String Username = getIntent().getExtras().getString("login");
        String Link = getIntent().getExtras().getString("html_url");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome Developer @" + Username +","  +  Link )
                .getIntent();
                return shareIntent;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }


}
