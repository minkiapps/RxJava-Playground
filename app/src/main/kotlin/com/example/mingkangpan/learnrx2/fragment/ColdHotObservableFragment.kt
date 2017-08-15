package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_coldhot_observables.*
import java.util.concurrent.TimeUnit

/**
 * Created by panmingk on 18/04/2017.
 */
class ColdHotObservableFragment : RxFragment(){

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_coldhot_observables, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		initColdObservable()
		initHotObservable()
	}

	private fun initColdObservable() {
		val coldObservables = Observable.interval(1000, TimeUnit.MILLISECONDS)

		val outPut = tv_fragment_coldhot_pbservables_cold_output
		btn_fragment_coldhot_pbservables_cold.setOnClickListener {
			outPut.text = ""
			compositeDisposables += coldObservables
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe { l ->
						outPut.append("Cold observable emits: $l\n")
					}
		}
	}

	private fun initHotObservable() {
		val hotObservables = Observable.interval(1000, TimeUnit.MILLISECONDS).publish()
		addDisposable(hotObservables.connect())

		val outPut = tv_fragment_coldhot_pbservables_hot_output
		btn_fragment_coldhot_pbservables_hot.setOnClickListener {
			outPut.text = ""
			compositeDisposables += hotObservables
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe { l ->
						outPut.append("Hot observable emits: $l\n")
					}
		}
	}
}