package com.nevinsjr.rxhub4k.api

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.rx3.rxQuery
import com.nevinsjr.rxhub4k.client.RxHubClient
import io.reactivex.rxjava3.core.Observable

/**
 * Perform a graphql query and emit the returned data on a RxJava Observable.
 */
fun <D : Operation.Data, T, V : Operation.Variables> RxHubClient.rx3query(query: Query<D, T, V>): Observable<T?> =
        this.apolloClient.rxQuery(query).map { it.data }
