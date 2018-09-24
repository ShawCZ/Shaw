package com.shaw.shaw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shaw.core.delegates.ShawDelegate;
import com.shaw.core.net.rest.RestClient;
import com.shaw.core.net.callback.ISuccess;
import com.shaw.core.net.rxjava.RxRestClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author by Shaw on 2018/9/24 11:03
 */
public class MainDelegate extends ShawDelegate {
    private static final String TAG = "MainDelegate";
    Button button;

    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Log.d(TAG, "onBindView: ");

        RestClient.bulider()
                .url("http://www.baidu.com/")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(_mActivity, response, Toast.LENGTH_SHORT).show();
                    }
                })
                .loader(getProxyActivity())
                .build()
                .get();

        RxRestClient.bulider()
                .url("http://www.baidu.com/")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: s = "+s );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onNext: Throwable = "+e );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
