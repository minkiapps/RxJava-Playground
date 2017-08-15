package com.example.mingkangpan.learnrx2.db

import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Created by panmingk on 10/04/2017.
 */
interface DataBase {

	fun getFirstName() : Observable<String>

	fun getLastName() : Observable<String>

	fun getAntiSocialScheduler() : Scheduler

	fun getAntiSocialFirstName() : Observable<String>

	fun getAntiSocialLastName() : Observable<String>
}