package com.example.evalucacionprimerparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class Television extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    private VideoView video;
    private ArrayList<Integer> videoList = new ArrayList<>();
    int currVideo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television);
        video=(VideoView) findViewById(R.id.VideoView);
        video.setMediaController(new MediaController(Television.this));
        video.setOnCompletionListener(Television.this);

        videoList.add(R.raw.movie);
        videoList.add(R.raw.movie2);
        setVideo(videoList.get(0));
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