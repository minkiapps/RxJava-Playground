package com.example.mingkangpan.learnrx2.DI

import java.util.*

/**
 * Created by panmingk on 10/04/2017.
 */
class Dependency private constructor(){

	private val objectMap = Collections.synchronizedMap(HashMap<Class<*>, DependencyProvider<*>>())

	infix fun init(builder : Dependency.() -> Unit) {
		builder(this)
	}

	infix inline fun <reified T : Any>instance(noinline providerFactory : () -> T) {
		setProvider(T::class.java, NewInstanceProvider(providerFactory))
	}

	infix inline fun <reified T : Any>singelton(instance : T) {
		setProvider(T::class.java, SingeltonProvider(instance))
	}

	fun setProvider(key : Class<*>, dependencyProvider: DependencyProvider<*>) {
		objectMap[key] = dependencyProvider
	}

	inline fun <reified T>resolve() : T {
		return resolve(T::class.java) as T
	}

	infix fun resolve(clazz: Class<*>) : Any {
		if(!objectMap.containsKey(clazz)) {
			throw IllegalStateException("Dependency with key $clazz was never set!")
		}
		return objectMap[clazz]!!.provide()
	}

	companion object {
		val INSTANCE: Dependency by lazy {
			Dependency()
		}
	}

}