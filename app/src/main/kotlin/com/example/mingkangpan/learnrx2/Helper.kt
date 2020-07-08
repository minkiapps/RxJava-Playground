package com.example.mingkangpan.learnrx2

import android.util.Log
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * Created by panmingk on 10/04/2017.
 */
fun Any.logWithCurrentThread(message: String) = debug("${Thread.currentThread()}: $message")

infix fun Any.debug(message: String) = Log.d(this.javaClass.simpleName, message)

class SimpleFuture<V>(val work : () -> V) : Future<V> {

	override fun isCancelled(): Boolean = TODO("not implemented")

	override fun isDone(): Boolean = TODO("not implemented")

	override fun get(): V = work.invoke()

	override fun get(timeout: Long, unit: TimeUnit?): V = TODO("not implemented")

	override fun cancel(mayInterruptIfRunning: Boolean): Boolean = TODO("not implemented")
}