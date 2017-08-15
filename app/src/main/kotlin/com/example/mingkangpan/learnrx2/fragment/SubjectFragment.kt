package com.example.mingkangpan.learnrx2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import kotlinx.android.synthetic.main.fragment_subject.*
import java.util.*

/**
 * Created by panmingk on 19/04/2017.
 */
class SubjectFragment : RxFragment(){

	private val publishSubject = PublishSubject.create<Int>()
	private val behaviourSubject = BehaviorSubject.create<Int>()
	private val replaySubject = ReplaySubject.create<Int>()
	private val random = Random()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_subject, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		initPublishSubject()
		initBehaviourSubject()
		initReplaySubject()
	}

	private fun initPublishSubject() {
		var emitCount = 0
		btn_fragment_subject_publish.setOnClickListener {
			btn_fragment_subject_publish.text = "Publish Subject (${++emitCount})"
			publishSubject.onNext(random.nextInt(10))
		}

		var subCount = 0
		btn_fragment_subjects_publish_addsub.setOnClickListener {
			tv_fragment_subject_publish_subscriber_info.text = "Publish Subject Subscriber: ${++subCount}"

			compositeDisposables += publishSubject.subscribe { i ->
				tv_fragment_subjects_output.append("Publish Subject emits: $i\n")
			}
		}
	}

	private fun initBehaviourSubject() {
		var emitCount = 0
		btn_fragment_subject_behaviour.setOnClickListener {
			btn_fragment_subject_behaviour.text = "Behaviour Subject (${++emitCount})"
			behaviourSubject.onNext(random.nextInt(10))
		}

		var subCount = 0
		btn_fragment_subjects_behaviour_addsub.setOnClickListener {
			tv_fragment_subject_publish_subscriber_info.text = "Behaviour Subject Subscriber: ${++subCount}"

			compositeDisposables += behaviourSubject.subscribe {i ->
				tv_fragment_subjects_output.append("Behaviour Subject emits: $i\n")
			}
		}
	}

	private fun initReplaySubject() {
		var emitCount = 0
		btn_fragment_subjects_replay.setOnClickListener {
			btn_fragment_subjects_replay.text = "Replay Subject (${++emitCount})"
			replaySubject.onNext(random.nextInt(10))
		}

		var subCount = 0
		btn_fragment_subjects_replay_addsub.setOnClickListener {
			tv_fragment_subject_publish_subscriber_info.text = "Replay Subject Subscriber: ${++subCount}"

			compositeDisposables += replaySubject.subscribe {i ->
				tv_fragment_subjects_output.append("Replay Subject emits: $i\n")
			}
		}
	}


}