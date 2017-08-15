package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import com.example.mingkangpan.learnrx2.SimpleFuture
import com.example.mingkangpan.learnrx2.logWithCurrentThread
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_operators.*

/**
 * Created by panmingk on 05/05/2017.
 */
class OperatorsFragment : RxFragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_operators, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		button_create.setOnClickListener {
			Observable.create<Int> { e ->
				e.onNext(1)
				e.onComplete()
			}.subscribe({ r ->
				logWithCurrentThread("OnNext $r")
			}, { }, {
				logWithCurrentThread("OnComplete")
			})
		}

		button_just.setOnClickListener {
			Observable.just(1).subscribe({ r ->
				logWithCurrentThread("OnNext $r")
			}, { }, {
				logWithCurrentThread("OnComplete")
			})
		}

		button_from.setOnClickListener {
			Observable.fromArray(0, 1, 2, 3, 4, 5).subscribe({ r ->
				logWithCurrentThread("OnNext $r")
			}, { }, {
				logWithCurrentThread("OnComplete")
			})
		}

		button_defer.setOnClickListener {
			Observable.defer {
				Observable.just(SimpleFuture {
					Thread.sleep(5000)
					1
				}.get())
			}.subscribe({ r ->
				logWithCurrentThread("OnNext $r")
			}, { }, {
				logWithCurrentThread("OnComplete")
			})
		}

		button_map.setOnClickListener {
			Observable.fromArray(0, 1, 2, 3, 4, 5)
					.map { i -> "Text $i" }
					.subscribe({ r ->
						logWithCurrentThread("OnNext $r")
					}, { }, {
						logWithCurrentThread("OnComplete")
					})
		}

		button_flat_map.setOnClickListener {
			Observable.fromArray(0, 1, 2, 3, 4, 5)
					.flatMap { i -> Observable.just("Text $i") }
					.subscribe({ r ->
						logWithCurrentThread("OnNext $r")
					}, { }, {
						logWithCurrentThread("OnComplete")
					})
		}

		button_filter.setOnClickListener {
			Observable.fromArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
					.filter { i -> i % 3 == 0 }
					.subscribe({ r ->
						logWithCurrentThread("OnNext $r")
					}, { }, {
						logWithCurrentThread("OnComplete")
					})
		}

		button_groupby.setOnClickListener {
			Observable.fromArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
					.groupBy { i -> i % 2 == 0 }
					.flatMapMaybe { g -> g.reduce { t1, t2 -> t1 + t2 } }
					.subscribe({ r ->
						logWithCurrentThread("OnNext $r")
					}, { }, {
						logWithCurrentThread("OnComplete")
					})
		}

		button_distinct.setOnClickListener {
			Observable.fromArray(0, 1, 1, 2, 4, 5, 2, 7, 8, 1)
					.distinct()
					.subscribe({ r ->
						logWithCurrentThread("OnNext $r")
					}, { }, {
						logWithCurrentThread("OnComplete")
					})
		}

		/*button_merge.setOnClickListener {
			arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).toObservable()
					.mergeWith(arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).toObservable())
					.subscribeOn(Schedulers.io())
					.subscribe({ r ->
						logWithCurrentThread("OnNext $r")
					}, { }, {
						logWithCurrentThread("OnComplete")
					})
		}

		button_concat.setOnClickListener {
			Observable.fromArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).concatWith {
				Observable.fromArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
			}.subscribe({ r ->
				logWithCurrentThread("OnNext $r")
			}, { }, {
				logWithCurrentThread("OnComplete")
			})
		}*/
	}
}