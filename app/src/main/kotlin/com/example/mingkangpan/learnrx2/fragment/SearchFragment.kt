package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChanges
import com.jakewharton.rxbinding2.widget.text
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit

/**
 * Created by mingkangpan on 17/04/2017.
 */
class SearchFragment : RxFragment(){

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_search, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		initSearchFragment()
	}

	private fun initSearchFragment() {
		val searchView = serachview_fragment_search
		val called = tv_fragment_search_called
		val lastText = tv_fragment_search_last_text

		var apiCalls = 0
		compositeDisposables += searchView.queryTextChanges()
				.debounce(400, TimeUnit.MILLISECONDS)
				.filter { c -> c.length >= 3 }
				.map { c -> "Last Search Text: $c" }
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext { called.text = "Search called: ${++apiCalls}" }
				.subscribe(lastText.text())
	}
}