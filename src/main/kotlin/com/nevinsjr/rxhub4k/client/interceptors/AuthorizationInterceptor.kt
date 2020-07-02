package com.nevinsjr.rxhub4k.client.interceptors

import okhttp3.Interceptor
import okhttp3.Response

// TODO: change token to client config
class AuthorizationInterceptor(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .addHeader("Authorization", token)
                .build()

        return chain.proceed(request)
    }
}
