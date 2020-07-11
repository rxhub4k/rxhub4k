package com.nevinsjr.rxhub4k.reactor

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import reactor.core.publisher.Flux

@JvmSynthetic
inline fun <T> ApolloCall<T>.flux(): Flux<Response<T>> =
        ReactorApollo.from(this)

/**
 * Creates a new [ApolloQueryCall] call and then converts it to a [Flux].
 *
 * The number of emissions this Observable will have is based on the
 * [com.apollographql.apollo.fetcher.ResponseFetcher] used with the call.
 */
@JvmSynthetic
inline fun <D : Operation.Data, T, V : Operation.Variables> ApolloClient.fluxQuery(
    query: Query<D, T, V>,
    configure: ApolloQueryCall<T>.() -> ApolloQueryCall<T> = { this }
): Flux<Response<T>> = query(query).configure().flux()
