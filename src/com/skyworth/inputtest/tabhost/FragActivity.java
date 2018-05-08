package com.skyworth.inputtest.tabhost;

import com.skyworth.inputtest.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FragActivity extends FragmentActivity implements OnClickListener {

    private ImageView mBtn1, mBtn2, mBtn3, mBtn4;
    private ImageView mSelImg;
    private RelativeLayout mTabLayout;
    private FragmentManager mFragmentManager;
    private LinearLayout content_container;
    private Context mContext;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.activity_frag);

        mContext = this;
        initView();
        showFirstFrag();
        testBroad();
    }

    private void testBroad() {
        Intent cooIntent = new Intent();
        cooIntent.setAction("com.skyworth.tv.GO_TO_CHANNEL");
        String passChannelName = mContext.getString(R.string.coocaa_test_channelname);
        cooIntent.putExtra("channelName", passChannelName);
        Log.i("Sdt_Zyt", " testBroad ");
        mContext.sendBroadcast(cooIntent);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
    }

    private void initView() {
        mTabLayout = (RelativeLayout) findViewById(R.id.tab_container);
        mBtn1 = (ImageView) findViewById(R.id.tab_bt_1);
        mBtn2 = (ImageView) findViewById(R.id.tab_bt_2);
        mBtn3 = (ImageView) findViewById(R.id.tab_bt_3);
        mBtn4 = (ImageView) findViewById(R.id.tab_bt_4);

        content_container = (LinearLayout) findViewById(R.id.content_container);
        mSelImg = (ImageView) findViewById(R.id.tab_bg_view);
        LayoutParams lp = mSelImg.getLayoutParams();
        lp.width = mTabLayout.getWidth() / 4;
    }

    private void showFirstFrag() {
        Fragment mFragment = new FirFragment();
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        mTransaction.commit();
        mTransaction.replace(R.id.content_container, mFragment);
    }

}
