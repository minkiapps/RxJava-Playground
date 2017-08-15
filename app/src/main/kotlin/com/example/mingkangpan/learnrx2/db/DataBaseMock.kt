package com.example.mingkangpan.learnrx2.db

import com.example.mingkangpan.learnrx2.logWithCurrentThread
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

/**
 * Created by panmingk on 10/04/2017.
 */
class DataBaseMock : DataBase {

	private val antiSocialScheduler = Schedulers.from(Executors.newSingleThreadExecutor { r ->
		val thread = Thread(r)
		thread.name = ANTISOCIAL_THREADNAME_PREFIX
		thread
	})

	override fun getFirstName(): Observable<String> = Observable.defer { Observable.just(getStringWithDelay("Max", 30)) }

	override fun getLastName(): Observable<String> = Observable.defer { Observable.just(getStringWithDelay("Musterman", 100)) }

	private fun getStringWithDelay(value : String, delayMillis : Long) : String {
		logWithCurrentThread("Fetching $value from Db")
		Thread.sleep(delayMillis)
		return value
	}

	override fun getAntiSocialScheduler() = antiSocialScheduler

	override fun getAntiSocialFirstName(): Observable<String> = Observable.create { e ->
		checkIfRunsOnAssiThread()
		e.onNext(getStringWithDelay("Max", 30))
		e.onComplete()
	}

	override fun getAntiSocialLastName(): Observable<String> = Observable.create { e ->
		checkIfRunsOnAssiThread()
		e.onNext(getStringWithDelay("Musterman", 100))
		e.onComplete()
	}

	private fun checkIfRunsOnAssiThread() {
		if (!Thread.currentThread().name.startsWith(ANTISOCIAL_THREADNAME_PREFIX)) {
			throw IllegalStateException("This must be executed on the Anti Social Thread!")
		}
	}

	companion object {
		private const val ANTISOCIAL_THREADNAME_PREFIX = "ANTI_SOCIAL_THREAD"
	}
}