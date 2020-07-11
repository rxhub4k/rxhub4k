package com.nevinsjr.rxhub4k.api

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.nevinsjr.rxhub4k.client.RxHubClient
import com.nevinsjr.rxhub4k.reactor.fluxQuery
import reactor.core.publisher.Flux

/**
 * Perform a graphql query and emit the returned data on a Reactor Flux.
 */
fun <D : Operation.Data, T, V : Operation.Variables> RxHubClient.reactorQuery(query: Query<D, T, V>): Flux<T?> =
        this.apolloClient.fluxQuery(query).map { it.data }
