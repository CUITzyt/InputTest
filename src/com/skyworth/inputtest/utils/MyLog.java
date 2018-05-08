package com.skyworth.inputtest.utils;

import android.util.Log;

public class MyLog {

	private static final String TAG = "Sdt_Zyt";
	private static final String PRE = "::::::::     ";

	public static void print(String msg, String content) {
		Log.i(TAG + msg + PRE, content);
	}
	public static void Out(String msg) {
		Log.i(TAG + PRE, msg);
	}
}
