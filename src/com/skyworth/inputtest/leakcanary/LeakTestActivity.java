package com.skyworth.inputtest.leakcanary;

import com.skyworth.inputtest.R;
import com.skyworth.inputtest.application.MyApplication;
import com.squareup.leakcanary.watcher.RefWatcher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class LeakTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_test);
        TextView textView = (TextView) findViewById(R.id.test_text_view);
        TestDataModel.getInstance().setRetainedTextView(textView);
        RefWatcher mRefWatcher = MyApplication.getRefWatcher(this);
        
        mRefWatcher.watch(this);
    }
}