package com.nevinsjr.rxhub4k.client.builders.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * An OkHttp interceptor that adds the given oauth token to the headers
 * of requests to the downstream API.
 */
internal class AuthorizationInterceptor(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .addHeader("Authorization", token)
                .build()

        return chain.proceed(request)
    }
}
