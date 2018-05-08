package com.skyworth.inputtest.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

public class Utils {

    private static final String PFBAR_ZIPFILE_NAME = "unzip";

    // date to unix time
    public static long getStringToDate(String mDateStr) {
        long ret = -1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = formatter.parse(mDateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ret = date.getTime();
        return ret;
    }

    // get time of now
    public static String getNowTime() {
        String ret = null;
        long time = System.currentTimeMillis();
        Date d1 = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ret = formatter.format(d1);

        return ret;
    }

    // unzip file
    public static boolean unZipFile(String zipFileName, String targetDirPath) {
        ZipEntry zipEntry = null;
        try {
            ZipFile zipFile = new ZipFile(zipFileName, "UTF-8");
            Enumeration e = zipFile.getEntries();
            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();
                String zipPath = targetDirPath + File.separator + zipEntry.getName().trim();
                if (zipEntry.isDirectory()) {
                    File f = new File(zipPath);
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    continue;
                }

                File f = new File(zipPath);
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();

                InputStream in = zipFile.getInputStream(zipEntry);
                FileOutputStream out = new FileOutputStream(f);

                byte[] by = new byte[1024];
                int c;
                while ((c = in.read(by)) != -1) {
                    out.write(by, 0, c);
                }
                in.close();
                out.close();
            }
            zipFile.close();
            if (hasSubFiles(targetDirPath)) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // whether has sub files
    private static boolean hasSubFiles(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            return false;
        }

        if (file.isFile()) {
            return false;
        }

        if (file.listFiles().length > 0) {
            return true;
        }

        return false;
    }

    public static void deleteFile(String file) {
        File f = new File(file);
        deleteFile(f);
    }

    public static void deleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                deleteFile(childFiles[i]);
            }
            file.delete();
        }
    }

    public static void handlePfBarAssetsConfig(InputStream srcFilePathInputStream, String dstFilePath) {
        File file1 = new File(dstFilePath);
        String dstFilePathNew = dstFilePath + File.separator + Const.AD_ZIPFILE_NAME;
        if (!file1.exists())
            file1.mkdir();
        try {
            File f = new File(dstFilePathNew);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            inputStream2File(srcFilePathInputStream, f);
            srcFilePathInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (fileExist(dstFilePathNew)) {
            unZipFile(dstFilePathNew, (new File(dstFilePathNew)).getParent());
            deleteFile(dstFilePathNew);
        }
    }

    public static boolean fileExist(String file) {
        File cacheDir = new File(file);
        if (cacheDir.exists()) {
            return cacheDir.canWrite();
        }

        return false;
    }

    public static void inputStream2File(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
        } catch (IOException e) {
        }
    }

    public static String getAdWebFile(Context context) {
        String ret = null;

        ret = "file://" + context.getFilesDir().getAbsolutePath() + File.separator + Const.AD_UNZIP_PATH
                + File.separator + Const.AD_WEBFILE_NAME;
        MyLog.Out(" ad web file is  " + ret);
        return ret;
    }

    /**
     * 得到Assets里面相应的文件流
     * 
     * @param fileName
     * @return
     */
    public static InputStream getAssetsStream(Context context, String fileName) {
        InputStream is = null;
        try {
            is = context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    public static boolean writeContentToFile(InputStream stream, String filePath) {
        if (TextUtils.isEmpty(filePath) || (stream == null)) {
            return false;
        }

        File f = new File(filePath);
        OutputStream os;
        try {
            os = new FileOutputStream(f);
            copyStream(stream, os);
            os.close();
            stream.close();

            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;

    }

    private static void copyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
            bytes = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
