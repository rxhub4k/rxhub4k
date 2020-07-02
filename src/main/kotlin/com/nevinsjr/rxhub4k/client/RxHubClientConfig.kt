package com.nevinsjr.rxhub4k.client

data class RxHubClientConfig(
    /**
     * The GitHub-issued token the client should use for authentication.
     */
    val oauthToken: String,
    /**
     * The URL of the GitHub server.  If none is provided, the config
     * will default to public GitHub.
     */
    val serverUrl: String = "https://api.github.com/graphql"
)
