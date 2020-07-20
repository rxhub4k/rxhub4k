package com.nevinsjr.rxhub4k.api

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.coroutines.toFlow
import com.nevinsjr.rxhub4k.client.RxHubClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Perform a graphql query and emit the returned data on Coroutines Flow.
 */
@ExperimentalCoroutinesApi
fun <D : Operation.Data, T, V : Operation.Variables> RxHubClient.coroutinesFlowQuery(query: Query<D, T, V>): Flow<T?> =
    this.apolloClient.query(query).toFlow().map { it.data }
