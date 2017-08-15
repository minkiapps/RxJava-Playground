package com.example.mingkangpan.learnrx2.breakfast

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * Created by panmingk on 08/05/2017.
 */
class BreakfastFactoryImpl : BreakfastFactory {

	private val breadRelay = PublishRelay.create<Bread>()
	private val cheeseRelay = PublishRelay.create<Cheese>()

	init {
		Thread(Runnable {
			while (true) {
				Thread.sleep(500)
				breadRelay.accept(Bread())
				Thread.sleep(500)
				breadRelay.accept(Bread())
				Thread.sleep(1000)
				cheeseRelay.accept(Cheese())
			}
		}).start()
	}

	override fun getBreadObservable(): Observable<Bread> = breadRelay

	override fun getCheeseObservable(): Observable<Cheese> = cheeseRelay
}