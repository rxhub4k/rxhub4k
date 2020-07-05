package com.nevinsjr.rxhub4k.client.builders

import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

/**
 * Describes a custom execution context.
 */
data class ExecutionContext(
    val executor: Executor,
    val executorService: ExecutorService
)
