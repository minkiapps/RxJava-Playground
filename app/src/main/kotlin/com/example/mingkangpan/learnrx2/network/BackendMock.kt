package com.example.mingkangpan.learnrx2.network

import android.os.Looper
import android.os.NetworkOnMainThreadException
import com.example.mingkangpan.learnrx2.SimpleFuture
import com.example.mingkangpan.learnrx2.logWithCurrentThread
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.Executors

/**
 * Created by panmingk on 10/04/2017.
 */
class BackendMock : Backend {

	private val backendScheduler = Schedulers.from(Executors.newSingleThreadExecutor())

	override fun nextRandomIntObs(): Observable<Int> = Observable.defer {
		Observable.just(work({
			Thread.sleep(1000)
			Random().nextInt()
		}, "Generating random number"))
	}

	override fun nextRandomIntFuture() = SimpleFuture {
		work({
			Thread.sleep(1000)
			Random().nextInt()
		}, "Generating random number")
	}

	override fun hashObject(any: Any): Observable<Int> = Observable.defer {
		Observable.just(work({
			Thread.sleep(300)
			any.hashCode()
		}, "Hashing object: $any"))
	}

	override fun getScheduler(): Scheduler = backendScheduler

	private fun <T> work(work: () -> T, logMessage: String): T {
		checkIfNotMainThread()
		logWithCurrentThread(logMessage)
		return work.invoke()
	}

	private fun checkIfNotMainThread() {
		if (Thread.currentThread() === Looper.getMainLooper().thread) {
			throw NetworkOnMainThreadException()
		}
	}
}