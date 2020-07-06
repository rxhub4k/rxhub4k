package com.nevinsjr.rxhub4k.client.builders

import com.nevinsjr.rxhub4k.client.builders.interceptors.AuthorizationInterceptor
import java.util.concurrent.ExecutorService
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

/**
 * Factory function that encapsulates the need for an auth-enabled client.
 */
internal fun getRxHubHttpClientBuilder(oauthToken: String): OkHttpClient.Builder =
        OkHttpClient.Builder().addInterceptor(AuthorizationInterceptor("Bearer $oauthToken"))

/**
 * Factory function that encapsulates the need for a client with a custom execution context.
 */
internal fun getRxHubHttpClientBuilder(oauthToken: String, executorService: ExecutorService): OkHttpClient.Builder =
    getRxHubHttpClientBuilder(oauthToken).dispatcher(Dispatcher(executorService))
