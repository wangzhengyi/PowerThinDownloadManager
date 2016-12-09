package com.power.thin.downloadmanager;

import android.os.Build;
import android.os.StatFs;

public class ThinDownloadUtil {

    public static long getDataAvailSpace() {
        StatFs statFs = new StatFs("/data");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        } else {
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        }
    }
}