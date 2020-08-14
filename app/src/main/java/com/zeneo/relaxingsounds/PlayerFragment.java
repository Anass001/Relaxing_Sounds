package com.zeneo.relaxingsounds;

import android.content.ContentResolver;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class PlayerFragment extends Fragment {

    private int position;
    private MediaPlayer mediaPlayer;
    private ImageView playImageView;
    private ImageView volumeImageView;
    private RelativeLayout playRelativeLayout;
    private ImageView playerBackgroundImageView;
    private SeekBar volumeSeekBar;
    private boolean isMuted = false;
    private float volume;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerBackgroundImageView = view.findViewById(R.id.player_background_image_view);
        playImageView = view.findViewById(R.id.play_image_view);
        volumeImageView = view.findViewById(R.id.volume_status);
        playRelativeLayout = view.findViewById(R.id.play_rl);
        volumeSeekBar = view.findViewById(R.id.volume_seek_bar);

        position = PlayerFragmentArgs.fromBundle(getArguments()).getPosition();
        playerBackgroundImageView.setImageResource(MainFragment.items.get(position).getImage());
        mediaPlayer = MediaPlayer.create(getContext(), MainFragment.items.get(position).getRaw());
        mediaPlayer.setLooping(true);

        startMediaPlayer();

        playRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlMediaPlayer();
            }
        });

        volumeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMuted) {
                    setVolume(volume);
                } else {
                    setVolume(0);
                }
            }
        });

        volumeSeekBar.setMax(100);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setVolume((float) progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void controlMediaPlayer() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            startMediaPlayer();
        }
        else if(mediaPlayer != null && mediaPlayer.isPlaying()){
            pauseMediaPlayer();
        }
    }

    public void startMediaPlayer(){
        mediaPlayer.start();
        playImageView.setImageResource(R.drawable.ic_round_pause_24);
    }
    public void pauseMediaPlayer(){
        mediaPlayer.pause();
        playImageView.setImageResource(R.drawable.ic_round_play_arrow_24);
    }

    public void setVolume(float v) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(v, v);
        }
        if (v == 0) {
            volumeImageView.setImageResource(R.drawable.ic_baseline_volume_off_24);
            isMuted = true;
            return;
        } else if (isMuted){
            volumeImageView.setImageResource(R.drawable.ic_baseline_volume_up_24);
            isMuted = false;
        }
        volume = v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
}