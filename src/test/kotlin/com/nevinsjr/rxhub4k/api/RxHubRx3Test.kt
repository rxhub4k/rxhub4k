package com.nevinsjr.rxhub4k.api

import com.apollographql.apollo.api.toJson
import com.apollographql.apollo.exception.ApolloHttpException
import com.nevinsjr.rxhub4k.Repository.RepositoryPullRequestQuery
import com.nevinsjr.rxhub4k.`test-utils`.getImmediateExecutor
import com.nevinsjr.rxhub4k.`test-utils`.getImmediateExecutorService
import com.nevinsjr.rxhub4k.client.RxHubClient
import com.nevinsjr.rxhub4k.client.builders.ExecutionContext
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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

    val prListNoResults = RepositoryPullRequestQuery.Data(
            RepositoryPullRequestQuery.Repository(
                    pullRequests = RepositoryPullRequestQuery.PullRequests(
                            totalCount = 0,
                            pageInfo = RepositoryPullRequestQuery.PageInfo(
                                    endCursor = null,
                                    hasNextPage = false,
                                    hasPreviousPage = false,
                                    startCursor = null
                            ),
                            nodes = null
                    )
            )
    )

    private lateinit var rxHubClient: RxHubClient

    @BeforeEach
    fun setup() {
        val executionContext = ExecutionContext(getImmediateExecutor(), getImmediateExecutorService())
        rxHubClient = RxHubClient.build("someToken", mockServer.url("/").toString(), executionContext)
    }

    @Test
    fun query_returnsDataFromResponse() {
        // Arrange
        val query = RepositoryPullRequestQuery("someOwner", "someName")
        mockServer.enqueue(MockResponse().setBody(prListNoResults.toJson()))

        // Act
        // Assert
        rxHubClient.rx3query(query).test()
            .assertNoErrors()
            .assertComplete()
            .assertValue {
                assertEquals(prListNoResults, it)
                true
            }
    }

    @Test
    fun query_whenError_bubblesError() {
        // Arrange
        val query = RepositoryPullRequestQuery("someOwner", "someName")
        mockServer.enqueue(MockResponse().setResponseCode(401))

        // Act
        // Assert
        rxHubClient.rx3query(query).test()
                .assertError(ApolloHttpException::class.java)
                .assertNotComplete()
    }
}
