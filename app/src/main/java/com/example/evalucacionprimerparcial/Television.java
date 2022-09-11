package com.example.evalucacionprimerparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Television extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    private VideoView video;
    private ArrayList<Integer> videoList = new ArrayList<>();
    private FloatingActionButton fabMenu;
    private FloatingActionButton fabNext;
    private FloatingActionButton fabBack;
    int currVideo = 0;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television);
        video=(VideoView) findViewById(R.id.VideoView);
        fabMenu = findViewById(R.id.MiniMenuTelevision);
        fabBack = findViewById(R.id.MiniBtnBack);
        fabNext = findViewById(R.id.MiniBtnNext);

        video.setMediaController(new MediaController(Television.this));
        video.setOnCompletionListener(Television.this);

        videoList.add(R.raw.movie);
        videoList.add(R.raw.movie2);
        videoList.add(R.raw.movie3);
        setVideo(videoList.get(0));

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtomClicked();
            }
        });

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++currVideo;
                if (currVideo == videoList.size())
                    currVideo = 0;
                setVideo(videoList.get(currVideo));
            }
        });

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --currVideo;
                if (currVideo == -1)
                    currVideo = videoList.size()-1;
                setVideo(videoList.get(currVideo));
            }
        });

    }

    private void onAddButtomClicked() {
        setVisibility();
        setAnimation();
        clicked = !clicked;
    }

    private void setVisibility(){
        if(!clicked){
            fabBack.setVisibility(View.VISIBLE);
            fabNext.setVisibility(View.VISIBLE);
        }else{
            fabBack.setVisibility(View.INVISIBLE);
            fabNext.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(){
        if(!clicked)
        {
            Animation fromBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_anim);
            Animation rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_anim);

            fabBack.startAnimation(fromBottom);
            fabNext.startAnimation(fromBottom);
            fabMenu.startAnimation(rotateOpen);
        }else{
            Animation toButtom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_anim);
            Animation rotateClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_anim);

            fabBack.startAnimation(toButtom);
            fabNext.startAnimation(toButtom);
            fabMenu.startAnimation(rotateClose);
        }
    }

    private void setVideo(Integer id) {
        String path = "android.resource://" + getPackageName() + "/" + id;
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);
        video.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        AlertDialog.Builder obj = new AlertDialog.Builder(Television.this);
        obj.setTitle("Reproduccion terminada");
        MyListener m = new MyListener();
        obj.setPositiveButton("Repetir", m);
        obj.setNegativeButton("Siguiente",m);
        obj.setMessage("¿Siguiente?");
        obj.show();
    }

    class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which)
        {
            if (which == -1) {
                video.seekTo(0);
                video.start();
            }
            else {
                ++currVideo;
                if (currVideo == videoList.size())
                    currVideo = 0;
                setVideo(videoList.get(currVideo));
            }
        }
    }
}