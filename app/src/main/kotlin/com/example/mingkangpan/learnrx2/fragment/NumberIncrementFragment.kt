package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.text
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_number_increment.*

/**
 * Created by mingkangpan on 16/04/2017.
 */
class NumberIncrementFragment : RxFragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_number_increment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		initIncrementNumber()
		initFibonacciNumber()
	}

	private fun initIncrementNumber() {
		val button = fragment_numberincrement_button
		val textView = fragment_numberincrement_textview

		compositeDisposables += button.clicks()
				.map { 1 }
				.scan(0) { t1,t2 -> t1 + t2 }
				.map { i -> "Number: $i" }
				.subscribe(textView.text())
	}

	private fun initFibonacciNumber() {
		val button = fragment_numberincrement_fibonacci_button
		val textView = fragment_numberincrement_fibonacci_textview

		compositeDisposables += button.clicks()
				.scan(intArrayOf(0,1)) { t1,_ -> intArrayOf(t1[1], t1[0] + t1[1]) }
				.map { t -> "Number: ${t[1]}" }
				.subscribe(textView.text())
	}

}