package com.style.manager;

import android.app.Service;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Vibrator;

import example.app.MyApp;

public class VoiceSet {
	public static MediaPlayer mp = new MediaPlayer();
	private static AssetFileDescriptor assetFileDescriptor;
	public static boolean isSound = true;
	public static boolean isVibator = false;

	public static void playMsgNoting() {
		if (isSound) {
			try {
				// assetFileDescriptor =
				// MyApp.context.getResources().getAssets().openFd(msg.m3);
				mp.reset();
				// mp.setDataSource(assetFileDescriptor.getFileDescriptor());
				mp.setDataSource(MyApp.getAppContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
				mp.prepare();
				mp.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (isVibator) {
			playVibator(500);
		}
	}

	// 产生震动
	public static void playVibator(long timelong) {
		Vibrator vib = (Vibrator) MyApp.getAppContext().getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(timelong);
	}

}
