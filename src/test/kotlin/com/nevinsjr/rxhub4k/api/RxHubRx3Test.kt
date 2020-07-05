package com.nevinsjr.rxhub4k.api

import com.apollographql.apollo.api.toJson
import com.nevinsjr.rxhub4k.PullRequest.PrListQuery
import com.nevinsjr.rxhub4k.`test-utils`.getImmediateExecutor
import com.nevinsjr.rxhub4k.`test-utils`.getImmediateExecutorService
import com.nevinsjr.rxhub4k.client.RxHubClient
import com.nevinsjr.rxhub4k.client.builders.ExecutionContext
import io.reactivex.rxjava3.functions.Predicate
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

val PR_LIST = PrListQuery.Data(
        PrListQuery.Repository(
                pullRequests = PrListQuery.PullRequests(
                        totalCount = 0,
                        pageInfo = PrListQuery.PageInfo(
                                endCursor = null,
                                hasNextPage = false,
                                hasPreviousPage = false,
                                startCursor = null
                        ),
                        nodes = null
                )
        )
)

class RxHubRx3Test {

    companion object {
        private lateinit var mockServer: MockWebServer

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            mockServer = MockWebServer()
            mockServer.start(9900)
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            mockServer.shutdown()
        }
    }

    private lateinit var rxHubClient: RxHubClient

    @BeforeEach
    fun setup() {
        val executionContext = ExecutionContext(getImmediateExecutor(), getImmediateExecutorService())
        rxHubClient = RxHubClient.build("someToken", mockServer.url("/").toString(), executionContext)
    }

    @Test
    fun query_returnsDataFromResponse() {
        // Arrange
        val query = PrListQuery("someOwner", "someName")
        mockServer.enqueue(MockResponse().setBody(PR_LIST.toJson()))

        // Act
        // Assert
        rxHubClient.rx3query(query).test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(Predicate {
                    assertEquals(PR_LIST, it)
                    true
                })
    }
}
