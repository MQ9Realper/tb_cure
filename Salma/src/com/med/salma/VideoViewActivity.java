package com.med.salma;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {
	private VideoView my_video_player;
	private MediaController media_controller;
	private ProgressDialog progress_dialog;
	private int position = 0;
	public String file_path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_view);
		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.drawable.action_bar)));
		
		Intent intent = getIntent();
		file_path = intent.getStringExtra("video_name");

		// Set media controls
		if (media_controller == null) {
			media_controller = new MediaController(VideoViewActivity.this);
		}

		// Initialize video view
		my_video_player = (VideoView) findViewById(R.id.video_player);
		progress_dialog = new ProgressDialog(VideoViewActivity.this);
		progress_dialog.setTitle("TB Cure Video Player");
		progress_dialog.setMessage("Loading video..........");
		progress_dialog.setCancelable(true);
		progress_dialog.show();

		try {
			my_video_player.setMediaController(media_controller);
			my_video_player.setVideoPath("/storage/emulated/0/TBCureVideos/" + file_path);
		} catch (Exception e) {
			Toast.makeText(VideoViewActivity.this, "Error: " + e.getMessage(),
					Toast.LENGTH_LONG).show();
			e.getMessage();
		}

		my_video_player.requestFocus();
		my_video_player.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				progress_dialog.dismiss();
				my_video_player.seekTo(position);
				if (position == 0) {
					my_video_player.start();
				} else {
					my_video_player.pause();
				}

			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		// we use onSaveInstanceState in order to store the video playback
		// position for orientation change
		savedInstanceState.putInt("Position",
				my_video_player.getCurrentPosition());
		my_video_player.pause();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// we use onRestoreInstanceState in order to play the video playback
		// from the stored position
		position = savedInstanceState.getInt("Position");
		my_video_player.seekTo(position);
	}

}
