package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import com.jakewharton.rxbinding2.view.enabled
import com.jakewharton.rxbinding2.widget.checkedChanges
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_form.*

/**
 * Created by mingkangpan on 17/04/2017.
 */
class FormFragment : RxFragment(){

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_form, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		initForm()
	}

	private fun initForm() {
		val feedBack = edittext_fragment_form_feedback
		val name = edittext_fragment_form_name
		val checkBox = checkbox_fragment_form_accept
		val button = btn_fragment_form_send

		compositeDisposables += Observable.combineLatest(
				feedBack.textChanges(),
				name.textChanges(),
				checkBox.checkedChanges(),
				Function3<CharSequence, CharSequence, Boolean, Boolean> { t1, t2, t3 -> t1.length >= 5 && t2.length >= 3 && t3 })
				.subscribe(button.enabled())
	}
}