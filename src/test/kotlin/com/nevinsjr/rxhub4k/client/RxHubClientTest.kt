package com.nevinsjr.rxhub4k.client

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class RxHubClientTest {

    @Test
    fun client_shouldBeDefined() {
        // Arrange
        // Act
        val rxHubClient = RxHubClient.build("someToken")

        // Assert
        assertNotNull(rxHubClient)
    }

    @Test
    fun client_withoutServerUrl_shouldDefaultToPublicGitHub() {
        // Arrange
        // Act
        val rxHubClient = RxHubClient.build("someToken")

        // Assert
        assertEquals("https://api.github.com/graphql", rxHubClient.apolloClient.serverUrl.toString())
    }

    @Test
    fun client_withServerUrl_shouldSetServerurl() {
        // Arrange
        val expectedUrl = "https://someurl/graphql"

        // Act
        val rxHubClient = RxHubClient.build("someToken", expectedUrl)

        // Assert
        assertEquals(expectedUrl, rxHubClient.apolloClient.serverUrl.toString())
    }
}
