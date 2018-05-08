package com.skyworth.inputtest.alarm;

import com.skyworth.inputtest.utils.Const;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    private UpdateUIListener mUpdateUIListener;
    private TestUpdateUIListener mTestUpdateUIListener;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String mAction = intent.getAction();
        if (mAction.equals(Const.MY_ALARM_ONE_ACTION)) {
            String passStr = intent.getExtras().getString("ALARM_PARAM_1");
//            mUpdateUIListener.updateUI(passStr);
            mTestUpdateUIListener.testUpdateUI(passStr);
        }
    }

    public void setUpdateUIListener(UpdateUIListener updateUIListener) {
        mUpdateUIListener = updateUIListener;
    }
    
    public void setTestUpdateUIListener(TestUpdateUIListener testUpdateUIListener){
        mTestUpdateUIListener = testUpdateUIListener;
    }
    
    public interface TestUpdateUIListener{
        void testUpdateUI(String mStr);
    }
}
