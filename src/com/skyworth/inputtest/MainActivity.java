package com.skyworth.inputtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final String TAG = "Sdt_Zyt::::  ";

    private Button mBtn;
    private Button mBtn_1;
    private Process p;

    public String isPingSuccess(int pingNum, String m_strForNetAddress) {
        StringBuffer tv_PingInfo = new StringBuffer();
        try {

            p = Runtime.getRuntime().exec("/system/bin/ping -c " + pingNum + " " + m_strForNetAddress); // 10.83.50.111
            // m_strForNetAddress
            int status = p.waitFor();
            String result = "";
            if (status == 0) {
                result = "success";
            } else {
                result = "failed";
            }
            String lost = new String();
            String delay = new String();
            BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String str = new String();
            while ((str = buf.readLine()) != null) {
                str = str + "\r\n";
                Log.i(TAG, "str is " + str);
                tv_PingInfo.append(str);
            }
            Log.i("Sdt_Zyt::::  ", tv_PingInfo.toString());
            return tv_PingInfo.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn_1 = (Button) findViewById(R.id.btn_1);

        mBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-gen erated method stub
                        isPingSuccess(10, "192.168.1.104");
                    }
                }).start();
            }
        });

        mBtn_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        p.destroy();
                    }
                }).start();
            }
        });
        // new Thread(new Runnable() {
        //
        // @Override
        // public void run() {
        // // TODO Auto-generated method stub
        // String ipAddr = "192.168.1.104";
        // Process process = null;
        // String result = "";
        // Log.i(TAG, " to ping address ");
        // try {
        // // process = Runtime.getRuntime().exec("ping " + ipAddr);
        // process = Runtime.getRuntime().exec("adbd\n");
        //
        // InputStream input = process.getInputStream();
        // BufferedReader in = new BufferedReader(new InputStreamReader(input));
        // StringBuffer buffer = new StringBuffer();
        // String line = "";
        // while ((line = in.readLine()) != null) {
        // buffer.append(line);
        // // mTextView.append(line + "\r\n");
        // Log.i(TAG, line);
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }).start();
        // setListener();

        testWayToCode();
    }

    private void testWayToCode() {
        Timer  myTimer = new Timer();
        
    }

    // private void setListener() {
    // mBtn.setOnClickListener(new View.OnClickListener() {
    //
    // @Override
    // public void onClick(View arg0) {
    // // TODO Auto-generated method stub
    // // String ipAddr = mIpAddr.getText().toString();
    // Log.i(TAG, " begin to click button ");
    // String ipAddr = "192.168.1.104";
    // Process process = null;
    // String result = "";
    // try {
    // process = Runtime.getRuntime().exec("ping " + ipAddr);
    // InputStream input = process.getInputStream();
    // BufferedReader in = new BufferedReader(new InputStreamReader(input));
    // StringBuffer buffer = new StringBuffer();
    // String line = "";
    // while ((line = in.readLine()) != null) {
    // buffer.append(line);
    // // mTextView.append(line + "\r\n");
    // Log.i(TAG, line);
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    //
    // });
    // }
}
