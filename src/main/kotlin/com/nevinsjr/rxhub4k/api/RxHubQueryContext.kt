package com.nevinsjr.rxhub4k.api

/**
 * The context of a given query.
 */
data class RxHubQueryContext(
    /**
     * The owner of the repository to which the client will interact.
     */
    val repoOwner: String,
    /**
     * The repository with which the client will interact.
     */
    val repoName: String
)
