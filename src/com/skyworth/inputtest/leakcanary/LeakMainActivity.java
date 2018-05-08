package com.skyworth.inputtest.leakcanary;

import com.skyworth.inputtest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class LeakMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leak_mainlay);
        findViewById(R.id.btn_go_to_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTest();
            }
        });
    }

    private void goToTest() {
        Intent intent = new Intent(this, LeakTestActivity.class);
        startActivity(intent);
    }

}
