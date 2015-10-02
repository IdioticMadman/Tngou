/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.data.model.exception;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import retrofit.RetrofitError;

/**
 * @author Kela.King
 */
public class ApiException extends RuntimeException {

    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public ApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public static ApiException create(RetrofitError retrofitError) {
        Throwable throwable = retrofitError.getCause();

        switch (retrofitError.getKind().ordinal()) {
            case 0:
                if (throwable instanceof SocketTimeoutException)
                    return new NetworkException("socket timeout exception", throwable);
                if (throwable instanceof ConnectTimeoutException)
                    return new NetworkException("connect timeout exception", throwable);
                if (throwable instanceof MalformedURLException)
                    return new NetworkException("url error", throwable);
                return new NetworkException("network error", throwable);

            case 1:
                return new GsonConversionException("gson convert error", throwable);

            case 2:
                try {
                    ApiError apiError = (ApiError) retrofitError.getBodyAs(ApiError.class);

                    if (apiError == null)
                        break;

                    ServerException serverException = new ServerException("server response error:" +
                            " " + apiError.toString(), throwable, apiError);
                    return serverException;
                } catch (Exception e) {
                    return new GsonConversionException("gson convert error", throwable);
                }

            case 3:
                return new UnexpectedException("unexpected error", throwable);

            default:
                return new UnexpectedException("unexpected error", throwable);
        }

        return null;
    }
}
