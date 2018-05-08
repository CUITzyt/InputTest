package com.skyworth.inputtest.receiver;

import com.skyworth.inputtest.download.FileDownloadService;
import com.skyworth.inputtest.utils.MyLog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

    private static final String BOOT_STR = Intent.ACTION_BOOT_COMPLETED;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String receiveStr = intent.getAction();
        if (receiveStr.equals(BOOT_STR)) {
            MyLog.Out(" receive boot receiver and start service ");
            Intent backGService = new Intent(context, BackGService.class);
            context.startService(backGService);

            Intent downFileService = new Intent(context, FileDownloadService.class);
            context.startService(downFileService);
            // InputStream srcFilePathInputStream =
            // context.getAssets().open(ConstUtil.PFBAR_ZIPFILE_NAME);
            // context.getCacheDir()
            // Utils.handlePfBarAssetsConfig(srcFilePathInputStream,
            // dstFilePath);
        }
    }

}
