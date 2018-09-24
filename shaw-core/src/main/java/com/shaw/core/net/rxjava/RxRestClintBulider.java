package com.shaw.core.net.rxjava;

import android.content.Context;

import com.shaw.core.net.callback.IError;
import com.shaw.core.net.callback.IFailure;
import com.shaw.core.net.callback.IRequest;
import com.shaw.core.net.callback.ISuccess;
import com.shaw.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Author by Shaw on 2018/9/24 16:16
 */
public class RxRestClintBulider {

    private String mUrl = null;
    private final Map<String, Object> PARAMS = new WeakHashMap<>();
    private RequestBody mBody = null;
    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;

    RxRestClintBulider() {
    }

    public final RxRestClintBulider url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClintBulider params(Map<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RxRestClintBulider params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public final RxRestClintBulider raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClintBulider raw(RequestBody body) {
        this.mBody = body;
        return this;
    }

    public final RxRestClintBulider file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClintBulider file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClintBulider loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClintBulider loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }


    public RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mFile, mContext, mLoaderStyle);
    }


}
