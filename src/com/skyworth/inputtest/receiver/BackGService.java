package com.skyworth.inputtest.receiver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.skyworth.inputtest.utils.Const;
import com.skyworth.inputtest.utils.MyLog;
import com.skyworth.inputtest.utils.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BackGService extends Service {

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        unzipAssetsFile(Const.AD_ZIPFILE_NAME, Const.AD_UNZIP_PATH);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    private void unzipAssetsFile(String assetFileName, String unzipPath) {
        InputStream srcFilePathInputStream = null;
        try {
            srcFilePathInputStream = this.getAssets().open(assetFileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MyLog.Out(" about to unzip files ");
        String pfDirUnzipPath = this.getFilesDir().getAbsolutePath() + File.separator + unzipPath;
        Utils.handlePfBarAssetsConfig(srcFilePathInputStream, pfDirUnzipPath);
    }
}
