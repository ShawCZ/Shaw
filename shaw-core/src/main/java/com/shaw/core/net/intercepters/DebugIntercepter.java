package com.shaw.core.net.intercepters;

import android.support.annotation.RawRes;

import com.shaw.core.utils.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Author by Shaw on 2018/9/24 23:21
 */
public class DebugIntercepter extends BaseIntercepter {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugIntercepter(String debugUrl, int debugRawId) {
        DEBUG_URL = debugUrl;
        DEBUG_RAW_ID = debugRawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        } else {
            return chain.proceed(chain.request());
        }
    }
}
