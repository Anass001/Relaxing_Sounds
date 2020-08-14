package com.zeneo.relaxingsounds.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.MediaBrowserServiceCompat;

import java.util.List;

public class MediaPlaybackService extends MediaBrowserServiceCompat {

    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder playbackStateBuilder;
    private MediaSessionCompat.Callback mediaSessionCallback;

    private final static String TAG = "001";

    @Override
    public void onCreate() {
        super.onCreate();

        mediaSession = new MediaSessionCompat(this, TAG);

        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        playbackStateBuilder = new PlaybackStateCompat.Builder().setActions(PlaybackStateCompat.ACTION_PLAY |
                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(playbackStateBuilder.build());

        mediaSessionCallback = new MediaSessionCompat.Callback() {
            @Override
            public void onCommand(String command, Bundle extras, ResultReceiver cb) {
                super.onCommand(command, extras, cb);
            }


        };
        mediaSession.setCallback(mediaSessionCallback);

        setSessionToken(mediaSession.getSessionToken());
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return null;
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {

    }
}
