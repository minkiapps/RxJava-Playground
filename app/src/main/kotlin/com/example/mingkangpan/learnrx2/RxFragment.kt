package com.example.mingkangpan.learnrx2

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by panmingk on 04/05/2017.
 */
abstract class RxFragment : Fragment(){

	protected val compositeDisposables = CompositeDisposable()

	fun addDisposable(disposable: Disposable) {
		compositeDisposables.add(disposable)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		compositeDisposables.dispose()
	}
}