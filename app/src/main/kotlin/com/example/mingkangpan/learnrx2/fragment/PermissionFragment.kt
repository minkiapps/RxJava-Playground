package com.example.mingkangpan.learnrx2.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mingkangpan.learnrx2.R
import com.example.mingkangpan.learnrx2.RxFragment
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_permissions.*

/**
 * Created by panmingk on 18/04/2017.
 */
class PermissionFragment : RxFragment(){

	private val rxPermissions by lazy { RxPermissions(activity) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.fragment_permissions, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		init()
	}

	private fun init() {
		compositeDisposables += RxView.clicks(btn_fragment_permissions_camera)
				.compose(rxPermissions.ensure(Manifest.permission.CAMERA))
				.filter { it }
				.subscribe {
					Toast.makeText(activity, "Camera Permission granted", Toast.LENGTH_SHORT).show()
				}

		compositeDisposables += RxView.clicks(btn_fragment_permissions_disk)
				.compose(rxPermissions.ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
				.filter { it }
				.subscribe {
					Toast.makeText(activity, "Write External Storage Permission granted", Toast.LENGTH_SHORT).show()
				}

		compositeDisposables += RxView.clicks(btn_fragment_permissions_phone_bluetooth)
				.compose(rxPermissions.ensure(Manifest.permission.CALL_PHONE, Manifest.permission.BLUETOOTH))
				.filter { it }
				.subscribe {
					Toast.makeText(activity, "Phone&Bluetooth Permission granted", Toast.LENGTH_SHORT).show()
				}
	}

}