package net.notjustanna.psi.executor.service

import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

interface TaskExecutorService {
    fun task(period: Long, unit: TimeUnit, initialDelay: Long = 0, name: String? = null, block: () -> Unit): ScheduledFuture<*>

    fun queue(name: String? = null, block: () -> Unit): CompletableFuture<*>

    fun <T> compute(name: String? = null, block: () -> T): CompletableFuture<T>

    fun schedule(delay: Long, unit: TimeUnit, name: String? = null, block: () -> Unit): ScheduledFuture<*>
}

fun TaskExecutorService.asJavaExecutor(): ExecutorService {
    return this as? ExecutorService ?: WrapperExecutorService(this)
}