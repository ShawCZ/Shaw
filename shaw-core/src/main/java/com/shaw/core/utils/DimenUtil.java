package com.shaw.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.shaw.core.app.Shaw;

/**
 * Author by Shaw on 2018/9/24 18:15
 */
public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Shaw.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Shaw.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
