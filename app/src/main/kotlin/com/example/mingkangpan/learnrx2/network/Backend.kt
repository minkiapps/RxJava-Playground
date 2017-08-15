package com.example.mingkangpan.learnrx2.network

import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.Future

/**
 * Created by panmingk on 10/04/2017.
 */
interface Backend {

	fun nextRandomIntObs() : Observable<Int>

	fun nextRandomIntFuture() : Future<Int>

	fun hashObject(any : Any) : Observable<Int>

	fun getScheduler() : Scheduler
}