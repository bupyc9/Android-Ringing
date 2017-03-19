package com.bupyc9.ringing;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener {

    final int MAX_STREAMS = 5;
    final String LOG_TAG = "myLogs";

    SoundPool sp;

    int soundResource;
    int soundIdExplosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool.Builder()
                .setMaxStreams(MAX_STREAMS)
                .setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                )
                .build();

        sp.setOnLoadCompleteListener(this);

        try {
            soundIdExplosion = sp.load(getAssets().openFd("sound.ogg"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "soundExplosion = " + soundIdExplosion);
    }

    public void onClick(View view) {
        if (soundResource > 0) {
            sp.stop(soundResource);
        }

        soundResource = sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(LOG_TAG, "onLoadComplete, sampleId = " + sampleId + ", status = " + status);
    }
}
