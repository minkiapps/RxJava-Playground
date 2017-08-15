package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper
import kotlinx.android.synthetic.main.fragment_morphimage.*
import java.util.concurrent.TimeUnit

/**
 * Created by mingkangpan on 17/04/2017.
 */
class MorphImageFragment : RxFragment(){

	private var disposable : Disposable = DisposableHelper.DISPOSED

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_morphimage, container, false)
	}

	override fun onResume() {
		super.onResume()

		val view = morphcolorview_fragment_morphimage
		disposable = Observable.interval(0, 2000, TimeUnit.MILLISECONDS)
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(view)
	}

	override fun onPause() {
		super.onPause()
		disposable.dispose()
	}
}