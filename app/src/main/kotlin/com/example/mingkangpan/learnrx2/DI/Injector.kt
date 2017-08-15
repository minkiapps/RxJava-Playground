package com.example.mingkangpan.learnrx2.DI

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by panmingk on 10/04/2017.
 */
private object UNINITIALIZED_VALUE

@Suppress("UNCHECKED_CAST")
class InjectorProperty<out T>(private val key : Class<T>) : ReadOnlyProperty<Any, T> {

	private var value: Any? = UNINITIALIZED_VALUE

	@Synchronized
	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		if(value == UNINITIALIZED_VALUE) {
			value = Dependency.INSTANCE.resolve(key)
		}
		return value as T
	}

}

inline fun <reified T> inject() : InjectorProperty<T> where T : Any {
	return InjectorProperty(T::class.java)
}