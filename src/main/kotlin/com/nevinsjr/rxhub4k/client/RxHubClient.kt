package com.nevinsjr.rxhub4k.client

import com.apollographql.apollo.ApolloClient
import com.nevinsjr.rxhub4k.client.builders.ExecutionContext
import com.nevinsjr.rxhub4k.client.builders.getRxHubApolloClientBuilder
import com.nevinsjr.rxhub4k.client.builders.getRxHubHttpClientBuilder

/**
 * The entry point for the library, offering an API for GitHub queries
 * with reactive returns.
 */
class RxHubClient internal constructor(
    internal val apolloClient: ApolloClient
) {
    companion object {
        /**
         * Static factory for the RxHubClient
         * @param oauthToken The GitHub-issued token the client should use for authentication.
         * @param serverUrl The URL of the GitHub server.  If none is provided, the config
         *                  will default to public GitHub.
         */
        fun build(oauthToken: String, serverUrl: String = "https://api.github.com/graphql"): RxHubClient {

            val okHttpClient = getRxHubHttpClientBuilder(oauthToken).build()
            val apolloClient = getRxHubApolloClientBuilder(serverUrl, okHttpClient).build()

            return RxHubClient(apolloClient)
        }

        /**
         * Static factory for the RxHubClient
         * @param oauthToken The GitHub-issued token the client should use for authentication.
         * @param serverUrl The URL of the GitHub server.  If none is provided, the config
         *                  will default to public GitHub.
         * @param executionContext A custom thread context in which to run the client.
         */
        fun build(
            oauthToken: String,
            serverUrl: String = "https://api.github.com/graphql",
            executionContext: ExecutionContext
        ): RxHubClient {

            val okHttpClient = getRxHubHttpClientBuilder(oauthToken, executionContext.executorService).build()
            val apolloClient = getRxHubApolloClientBuilder(serverUrl, okHttpClient, executionContext.executor).build()

            return RxHubClient(apolloClient)
        }
    }
}
