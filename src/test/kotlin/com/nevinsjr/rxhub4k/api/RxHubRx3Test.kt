package com.nevinsjr.rxhub4k.api

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx3.rxQuery
import com.nevinsjr.rxhub4k.PullRequest.PrListQuery
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
 import com.nevinsjr.rxhub4k.client.RxHubClient
import io.mockk.every
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RxHubRx3Test {

    private lateinit var apolloStub: ApolloClient

    @BeforeEach
    fun setup() {
        apolloStub = mockk()
    }

    @Test
    fun query_returnsDataFromResponse() {
        // Arrange
        val query = PrListQuery("someOwner", "someName")
        val client = RxHubClient(apolloStub)
        val response: Response<PrListQuery> = mockk()

        every { response.data } returns query
        every { apolloStub.rxQuery(ofType(Query::class), mockk()) } returns Observable.just(response)

        // Act
        client.rx3query(query).subscribe {
            assertEquals(query, it)
        }
    }
}
