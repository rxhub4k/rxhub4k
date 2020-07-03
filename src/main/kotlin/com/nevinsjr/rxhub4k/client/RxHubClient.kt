package com.nevinsjr.rxhub4k.client

import com.apollographql.apollo.ApolloClient
import com.nevinsjr.rxhub4k.client.interceptors.AuthorizationInterceptor
import okhttp3.OkHttpClient

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
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor("Bearer $oauthToken"))
                    .build()

            val apolloClient = ApolloClient.builder()
                    .serverUrl(serverUrl)
                    .okHttpClient(okHttpClient)
                    .build()

            return RxHubClient(apolloClient)
        }
    }
}
