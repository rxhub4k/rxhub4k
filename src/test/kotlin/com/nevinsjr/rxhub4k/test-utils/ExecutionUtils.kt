package com.nevinsjr.rxhub4k.`test-utils`

import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

/**
 * Borrowed from Apollo-Android integration testing utils:
 * apollo-integration/src/test/java/com/apollographql/apollo/Utils.kt
 */
fun getImmediateExecutor(): Executor {
    return Executor { it.run() }
}

/**
 * Borrowed from Apollo-Android integration testing utils:
 * apollo-integration/src/test/java/com/apollographql/apollo/Utils.kt
 */
fun getImmediateExecutorService(): ExecutorService {
    return object : AbstractExecutorService() {
        override fun shutdown() = Unit

        override fun shutdownNow(): List<Runnable>? = null

        override fun isShutdown(): Boolean = false

        override fun isTerminated(): Boolean = false

        @Throws(InterruptedException::class)
        override fun awaitTermination(l: Long, timeUnit: TimeUnit): Boolean = false

        override fun execute(runnable: Runnable) = runnable.run()
    }
}
