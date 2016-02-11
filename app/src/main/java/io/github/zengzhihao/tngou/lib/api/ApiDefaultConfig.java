/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.lib.api;

import retrofit.Endpoint;
import retrofit.Endpoints;

/**
 * @author Kela.King
 */
public class ApiDefaultConfig {

    public static final Endpoint END_POINT = Endpoints.newFixedEndpoint("http://www.tngou.net/api",
            "base-url");
    public static final String IMG_URL = "http://tnfs.tngou.net/img";
}
