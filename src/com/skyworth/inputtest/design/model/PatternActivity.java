package com.skyworth.inputtest.design.model;

import com.skyworth.inputtest.R;
import com.skyworth.inputtest.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;

public class PatternActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        
        resumeAdapterPattern();
    }
    
    private void resumeAdapterPattern(){
        PowerSupplyDevice mDevice = new PowerSupplyDevice();
        MyVolAdapter mAdapter = new MyVolAdapter();
        Jotter mJotter = new Jotter();
        mAdapter.setmSupplyDevice(mDevice);
        mJotter.setmAdapter(mAdapter);
        
        MyLog.Out(" after trans the vol is  " + mJotter.inputVoltage());
    }
}
