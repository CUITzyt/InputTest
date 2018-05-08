package com.skyworth.inputtest.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyAlarmManager {

    private static MyAlarmManager mInstance;
    private static final Object lock = new Object();
    private Context mContext;
    private AlarmManager mAlarmManager;

    private MyAlarmManager(Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
    }

    public static MyAlarmManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (lock) {
                mInstance = new MyAlarmManager(context);
            }
        }
        return mInstance;
    }

    public void addAlarmOnce(String action, int requestCode, long time, String param) {
        Intent intent = new Intent(action);
        Bundle mBundle = new Bundle();
        mBundle.putString("ALARM_PARAM_1", param);
        intent.putExtras(mBundle);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, time, pi);
    }

    public void addAlarmRepeat(String action, int requestCode, long time, String param) {
        Intent intent = new Intent(action);
        Bundle mBundle = new Bundle();
        mBundle.putString("ALARM_PARAM_1", param);
        intent.putExtras(mBundle);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, time, pi);
        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 5 * 1000, pi);
    }

    public void cancelAlarm(String action, int requestCode) {
        Intent intent = new Intent(action);
        PendingIntent sender = PendingIntent.getBroadcast(mContext, requestCode, intent, 0);
        mAlarmManager.cancel(sender);
    }
}
