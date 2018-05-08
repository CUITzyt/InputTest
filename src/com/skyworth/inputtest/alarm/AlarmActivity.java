package com.skyworth.inputtest.alarm;

import com.skyworth.inputtest.R;
import com.skyworth.inputtest.utils.Const;
import com.skyworth.inputtest.utils.HeritaedOne;
import com.skyworth.inputtest.utils.HeritaedTwo;
import com.skyworth.inputtest.utils.SkyToast;
import com.skyworth.inputtest.utils.Utils;
import com.skyworth.inputtest.utils.VirClsOne;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class AlarmActivity extends Activity {

    private String alarmTime = "2017-08-24 18:30:07";
    private String mAlarmStr = "This is alarm String+++ 11 !!!!";
    private Context mContext;
    private MyAlarmManager mMyAlarmManager;
    private VirClsOne mVirClsOne = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);
        mContext = this;
        mMyAlarmManager = MyAlarmManager.getInstance(mContext);

        addAlarmReceiver();
        // startOnceAlarm(Const.MY_ALARM_ONE_ACTION, Const.MY_ALARM_ONE_CODE,
        // alarmTime, mAlarmStr);
        startRepeatAlarm(Const.MY_ALARM_ONE_ACTION, Const.MY_ALARM_ONE_CODE, Utils.getNowTime(), mAlarmStr);
        mVirClsOne = new HeritaedTwo();
        
        mVirClsOne.virWayOne();
    }

    private void addAlarmReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Const.MY_ALARM_ONE_ACTION);
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        mContext.registerReceiver(alarmReceiver, intentFilter);
        // alarmReceiver.setUpdateUIListener(new UpdateUIListener() {
        //
        // @Override
        // public void updateUI(String mStr) {
        // // TODO Auto-generated method stub
        // SkyToast.showToast(mContext, mStr, 800, 200);
        // Log.i("Sdt_Zyt", " udpate UI show " + mStr);
        // }
        // });

        alarmReceiver.setTestUpdateUIListener(new AlarmReceiver.TestUpdateUIListener() {

            @Override
            public void testUpdateUI(String mStr) {
                // TODO Auto-generated method stub
                SkyToast.showToast(mContext, mStr, 800, 200);
                Log.i("Sdt_Zyt", " udpate UI show " + mStr);
            }

        });
    }

    private void startOnceAlarm(String action, int requestCode, String time, String param) {
        long timeLong = Utils.getStringToDate(time);
        mMyAlarmManager.addAlarmOnce(action, requestCode, timeLong, param);
    }

    private void startRepeatAlarm(String action, int requestCode, String time, String param) {
        long timeLong = Utils.getStringToDate(time);
        mMyAlarmManager.addAlarmRepeat(action, requestCode, timeLong, param);
    }
}
