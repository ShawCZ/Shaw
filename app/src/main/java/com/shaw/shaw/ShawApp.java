package com.shaw.shaw;

import android.app.Application;

import com.shaw.core.app.Shaw;

/**
 * Author by Shaw on 2018/9/14 00:59
 * @author hx
 */
public class ShawApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Shaw.init(this)
                .withApiHost("http://www.baidu.com/")
                .config();
    }
}
