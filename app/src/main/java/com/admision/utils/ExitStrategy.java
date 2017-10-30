package com.admision.utils;

import android.os.Handler;

public class ExitStrategy {

	private static boolean isAbletoExit = false;

	private static Handler h = new Handler();

	public static boolean canExit() {
		return isAbletoExit;
	}

	public static void startExitDelay(long delayMillis) {
		isAbletoExit = true;
		h.postDelayed(runnable, delayMillis);
	}

	static Runnable runnable = new Runnable() {

		@Override
		public void run() {
			isAbletoExit = false;
		}
	};

	public static void shutDown() {
		isAbletoExit = false;
		h.removeCallbacks(runnable);
	}

}
