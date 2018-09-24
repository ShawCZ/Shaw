package com.shaw.core.net;

import android.content.Context;

import com.shaw.core.net.callback.IError;
import com.shaw.core.net.callback.IFailure;
import com.shaw.core.net.callback.IRequest;
import com.shaw.core.net.callback.ISuccess;
import com.shaw.core.ui.loader.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Author by Shaw on 2018/9/24 16:16
 */
public class RestClintBulider {

    private String mUrl = null;
    private final Map<String, Object> PARAMS = new WeakHashMap<>();
    private IRequest mResquest = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;

    RestClintBulider() {
    }

    public final RestClintBulider url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClintBulider params(Map<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClintBulider params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public final RestClintBulider raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClintBulider raw(RequestBody body) {
        this.mBody = body;
        return this;
    }

    public final RestClintBulider success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClintBulider failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClintBulider error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClintBulider request(IRequest iRequest) {
        this.mResquest = iRequest;
        return this;
    }

    public final RestClintBulider loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClintBulider loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }


    public RestClient build() {
        return new RestClient(mUrl, PARAMS, mResquest, mSuccess, mFailure, mError, mBody, mContext, mLoaderStyle);
    }


}
