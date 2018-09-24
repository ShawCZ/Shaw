package com.shaw.core.net;

import android.content.Context;

import com.shaw.core.net.callback.IError;
import com.shaw.core.net.callback.IFailure;
import com.shaw.core.net.callback.IRequest;
import com.shaw.core.net.callback.ISuccess;
import com.shaw.core.net.callback.RequestCallbacks;
import com.shaw.core.ui.loader.LoaderStyle;
import com.shaw.core.ui.loader.ShawLoader;

import java.util.Map;

import retrofit2.Call;
import okhttp3.RequestBody;
import retrofit2.Callback;

/**
 * Author by Shaw on 2018/9/24 15:37
 */
public class RestClient {
    private final String URL;
    private final Map<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    RestClient(String url,
               Map<String, Object> params,
               IRequest request,
               ISuccess success,
               IFailure failure,
               IError error,
               RequestBody body,
               Context CONTEXT,
               LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS = params;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = CONTEXT;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClintBulider bulider() {
        return new RestClintBulider();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();

        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE!=null){
            ShawLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }


    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
