package com.example.mingkangpan.learnrx2.breakfast

import io.reactivex.Observable

/**
 * Created by panmingk on 08/05/2017.
 */
interface BreakfastFactory {

	fun getBreadObservable() : Observable<Bread>

	fun getCheeseObservable() : Observable<Cheese>
}

class Cheese

class Bread

data class YummyToast(val cheese : Cheese, val bread1: Bread, val bread2: Bread)