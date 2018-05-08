package com.skyworth.inputtest.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.skyworth.inputtest.utils.Const;
import com.skyworth.inputtest.utils.CustomerHttpClient;
import com.skyworth.inputtest.utils.MyLog;
import com.skyworth.inputtest.utils.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class FileDownloadService extends Service {

    private String downFilePath;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        retriveFilePath();

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                downloadFile(Const.MONITOR_SERVER_ADDRESS, downFilePath);
            }
        }).start();
    }

    private void retriveFilePath() {
        String sdPath = Environment.getExternalStorageDirectory().toString();
        String downFileName = Const.MONITOR_SERVER_ADDRESS.substring(Const.MONITOR_SERVER_ADDRESS.lastIndexOf("/") + 1,
                Const.MONITOR_SERVER_ADDRESS.length());
        downFilePath = sdPath + File.separator + downFileName;
        File downFile = new File(downFilePath);
        if (downFile.exists()) {
            downFile.delete();
        }
        try {
            downFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void downloadFile(String url, String dstPath) {
        MyLog.Out(" zyt download file start ");
        InputStream in = null;
        try {
            HttpClient client = CustomerHttpClient.getHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response;
            response = client.execute(request);
            HttpEntity httpEntity = response.getEntity();
            in = httpEntity.getContent();

            Utils.writeContentToFile(in, dstPath);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
