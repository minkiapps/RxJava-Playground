package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mingkangpan.learnrx2.DI.inject
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import com.example.mingkangpan.learnrx2.breakfast.Bread
import com.example.mingkangpan.learnrx2.breakfast.BreakfastFactory
import com.example.mingkangpan.learnrx2.breakfast.Cheese
import com.example.mingkangpan.learnrx2.breakfast.YummyToast
import com.example.mingkangpan.learnrx2.db.DataBase
import com.example.mingkangpan.learnrx2.logWithCurrentThread
import com.example.mingkangpan.learnrx2.network.Backend
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_stream.*

/**
 * Created by panmingk on 21/04/2017.
 */
class StreamFragment : RxFragment() {

	private val dataBase by inject<DataBase>()
	private val backend by inject<Backend>()

	private val breakfastFactory by inject<BreakfastFactory>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_stream, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		btn_fragment_stream_1.setOnClickListener {
			compositeDisposables += dataBase.getFirstName()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe { r ->
						logWithCurrentThread("FirstName: $r")
					}
		}

		btn_fragment_stream_2.setOnClickListener {
			compositeDisposables += dataBase.getFirstName()
					.flatMap { s -> backend.hashObject(s) }
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe { r ->
						logWithCurrentThread("FirstName Hash: $r")
					}
		}

		btn_fragment_stream_3.setOnClickListener {
			compositeDisposables += dataBase.getAntiSocialFirstName()
					.observeOn(Schedulers.io())
					.flatMap { s -> backend.hashObject(s) }
					.subscribeOn(dataBase.getAntiSocialScheduler())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe { r ->
						logWithCurrentThread("FirstName Hash: $r")
					}
		}

		btn_fragment_stream_4.setOnClickListener {
			compositeDisposables += dataBase.getAntiSocialFirstName()
					.subscribeOn(dataBase.getAntiSocialScheduler())
					.observeOn(AndroidSchedulers.mainThread())
					.flatMap { s -> Observable.just(Toast.makeText(activity, "FirstName: $s", Toast.LENGTH_SHORT).show()) }
					.observeOn(dataBase.getAntiSocialScheduler())
					.flatMap { dataBase.getAntiSocialLastName() }
					.observeOn(AndroidSchedulers.mainThread())
					.flatMap { s -> Observable.just(Toast.makeText(activity, "LastName: $s", Toast.LENGTH_SHORT).show()) }
					.subscribe()
		}

		btn_fragment_stream_5.setOnClickListener {
			compositeDisposables += Observable.zip(dataBase.getAntiSocialFirstName().subscribeOn(dataBase.getAntiSocialScheduler()),
							dataBase.getLastName(), BiFunction<String, String, String> { t1, t2 -> t1 + " " + t2 })
							.flatMap { s -> backend.hashObject(s) }
							.subscribeOn(Schedulers.io())
							.subscribe { r ->
								logWithCurrentThread("Name Hash: $r")
							}
		}

		btn_fragment_stream_6.setOnClickListener {
			compositeDisposables += Observable.zip(breakfastFactory.getBreadObservable()
					.take(6)
					.buffer(2), breakfastFactory.getCheeseObservable().take(3),
					BiFunction<List<Bread>, Cheese, YummyToast> { breads, cheese -> YummyToast(cheese, breads[0], breads[1]) })
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe({
						logWithCurrentThread("A new yummy Toast!")
					}, {
						//error ignored
					}, {
						logWithCurrentThread("3 yummy Toasts are made!")
					})
		}

	}
}