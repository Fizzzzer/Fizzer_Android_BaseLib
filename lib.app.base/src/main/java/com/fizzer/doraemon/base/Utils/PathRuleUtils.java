package com.fizzer.doraemon.base.Utils;

import android.os.Environment;

import java.io.File;
import java.util.Random;

/**
 * Created by Fizzer on 2019/5/23.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class PathRuleUtils {
    private static Random randomSeed = new Random();

    public static PathRuleUtils instance = new PathRuleUtils(".set.pathbase");

    public static void configDefaultPathRule(String appDirName) {
        instance.appDirName = appDirName;
    }

    private String appDirName;

    public PathRuleUtils(String appDirName) {
        this.appDirName = appDirName;
    }

    public String getRandomTempFilePath() {
        return createDirectoryIfNotExist(getAppDataBasePath() + "tmpfiles/") + getRandomName();
    }

    public String getRandomTempPackPath() {
        String dirName = randomSeed.nextDouble() + "";
        return createDirectoryIfNotExist(getAppDataBasePath() + "tmppack/" + dirName + "/");
    }

    public String getRandomName() {
        return MD5Utils.string2MD5(randomSeed.nextDouble() + "");
    }

    public String getAppDataBasePath() {
        return createDirectoryIfNotExist(getSdCardPath() + "/" + appDirName + "/");
    }

    public static String getSdCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String createDirectoryIfNotExist(String dir) {
        File dirF = new File(dir);
        if (!dirF.isDirectory()) {
            dirF.mkdirs();
        }
        return dir;
    }
}
