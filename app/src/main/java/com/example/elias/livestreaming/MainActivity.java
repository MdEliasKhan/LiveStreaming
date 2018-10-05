package com.example.elias.livestreaming;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private VideoView myVideoView;
    private String urlStream = "http://mhms9.airtel.tv/PLTV/88888888/224/3221226077/02.m3u8";
    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myVideoView = (VideoView)this.findViewById(R.id.myVideoView);
        MediaController mc = new MediaController(this);
        play = findViewById(R.id.btn_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!myVideoView.isPlaying()){
                        Uri uri = Uri.parse(urlStream);
                        myVideoView.setVideoURI(uri);
                        myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        myVideoView.pause();
                    }


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                myVideoView.requestFocus();
                myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                        myVideoView.start();
                    }
                });
            }
        });

    }
}
