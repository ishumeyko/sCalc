package com.shumz.scalc;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {

	MediaPlayer splash_melody;
	// Debug Logger Code BEGIN
	boolean isDebugLoggerEnabled = true;

	// Debug Logger Code END

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		if (isDebugLoggerEnabled) {
			finish();
			Intent openStartingPoint = new Intent(
					"com.shumz.scalc.SCALCACTIVITY");
			startActivity(openStartingPoint);
		} else {
			splash_melody = MediaPlayer.create(this,
					R.raw.first_lightning_splash_sound_5sec);
			splash_melody.start();

			Thread splash_melody_timer = new Thread() {
				public void run() {
					try {
						sleep(4000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						Intent openStartingPoint = new Intent(
								"com.shumz.scalc.SCALCACTIVITY");
						startActivity(openStartingPoint);
					}
				}

			};

			splash_melody_timer.start();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		splash_melody.release();
		finish();
	}

}
