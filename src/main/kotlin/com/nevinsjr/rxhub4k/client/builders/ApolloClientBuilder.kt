package com.nevinsjr.rxhub4k.client.builders

import com.apollographql.apollo.ApolloClient
import java.util.concurrent.Executor
import okhttp3.OkHttpClient

/**
 * Factory function that encapsulates the need for a url and a pre-configured OkHttp client.
 */
internal fun getRxHubApolloClientBuilder(url: String, client: OkHttpClient): ApolloClient.Builder =
    ApolloClient.builder()
        .serverUrl(url)
        .okHttpClient(client)

/**
 * Factory function that encapsulates the need for a client with a custom execution context.
 */
internal fun getRxHubApolloClientBuilder(url: String, client: OkHttpClient, executor: Executor): ApolloClient.Builder =
        getRxHubApolloClientBuilder(url, client).dispatcher(executor)
