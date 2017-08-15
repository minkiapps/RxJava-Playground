package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import com.example.mingkangpan.learnrx2.logWithCurrentThread
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.fragment_playground.*

/**
 * Created by mingkangpan on 17/04/2017.
 */
class KotlinPlayGroundFragment : RxFragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_playground, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		btn_fragment_playground_1.setOnClickListener { test1() }
		btn_fragment_playground_2.setOnClickListener { test1() }
		btn_fragment_playground_3.setOnClickListener { test1() }
		btn_fragment_playground_4.setOnClickListener { test1() }
	}

	private fun test1() {
		intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toObservable().subscribe { i -> logWithCurrentThread("Next Int: $i") }
	}

	private fun test2() {
		intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toObservable()
				.subscribe({ i -> logWithCurrentThread("Next Int: $i") }, {
					//onError
				}, {
					logWithCurrentThread("OnComplete")
				})
	}

	private fun test3() {

	}

	private fun test4() {

	}
}