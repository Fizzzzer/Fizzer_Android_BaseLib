package com.fizzer.doraemon.base.Http.Interceptor;

import android.util.Log;

import com.fizzer.doraemon.base.L.LogRoot;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Create by Fizzer on 2019/6/17
 * Email: Fizzer503@gmail.com
 * Description:网络日志拦截类
 */

public class LogInterceptor implements Interceptor {
    public static final String TAG = "lib.net";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logRequest(request);
        Response response = chain.proceed(request);
        return logResponse(response);
    }


    private void logRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();
            LogRoot.e(TAG, "url : " + url);
            LogRoot.e(TAG, "Method : " + request.method());
            if (headers != null && headers.size() > 0) {
                LogRoot.e(TAG, "Heads : " + headers.toString());
            }

            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    if (isText(mediaType)) {
                        LogRoot.e(TAG, "Params : " + bodyToString(request));
                    } else {
                        LogRoot.e(TAG, "Params : " + "maybe filePart, not print");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Response logResponse(Response response) {
        try {
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            ResponseBody body = clone.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    if (isText(mediaType)) {
                        String resp = body.string();
                        LogRoot.json(Log.ERROR, TAG, resp);

                        body = ResponseBody.create(mediaType, resp);

                        return response.newBuilder().body(body).build();
                    } else {
                        LogRoot.e(TAG, "data : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    private boolean isText(MediaType mediaType) {
        if (mediaType == null) return false;

        return ("text".equals(mediaType.subtype())
                || "json".equals(mediaType.subtype())
                || "xml".equals(mediaType.subtype())
                || "html".equals(mediaType.subtype())
                || "web-view-html".equals(mediaType.subtype())
                || "x-www-form-urlencoded".equals(mediaType.subtype()));
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
