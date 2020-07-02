package com.nevinsjr.rxhub4k.client

import com.apollographql.apollo.ApolloClient
import com.nevinsjr.rxhub4k.client.interceptors.AuthorizationInterceptor
import okhttp3.OkHttpClient

class RxHubClient private constructor(
    internal val apolloClient: ApolloClient
) {

    companion object {
        public fun build(config: RxHubClientConfig): RxHubClient {
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor("Bearer ${config.oauthToken}"))
                    .build()

            val apolloClient = ApolloClient.builder()
                    .serverUrl("https://api.github.com/graphql")
                    .okHttpClient(okHttpClient)
                    .build()

            return RxHubClient(apolloClient)
        }
    }
}
