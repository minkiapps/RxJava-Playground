package com.example.mingkangpan.learnrx2.DI

/**
 * Created by panmingk on 10/04/2017.
 */
interface DependencyProvider<out T> where T : Any{
	fun provide() : T
}

class SingeltonProvider<out T : Any>(private val instance: T) : DependencyProvider<T> {
	override fun provide(): T {
		return instance
	}
}

class NewInstanceProvider<out T : Any>(private val providerFactory : () -> T) : DependencyProvider<T> {
	override fun provide(): T {
		return providerFactory.invoke()
	}
}