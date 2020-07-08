package com.example.mingkangpan.learnrx2

import android.app.Application
import com.example.mingkangpan.learnrx2.DI.Dependency
import com.example.mingkangpan.learnrx2.breakfast.BreakfastFactory
import com.example.mingkangpan.learnrx2.breakfast.BreakfastFactoryImpl
import com.example.mingkangpan.learnrx2.db.DataBase
import com.example.mingkangpan.learnrx2.network.Backend
import com.example.mingkangpan.learnrx2.network.BackendMock
import com.example.mingkangpan.learnrx2.db.DataBaseMock

/**
 * Created by panmingk on 10/04/2017.
 */
class App : Application(){

	override fun onCreate() {
		super.onCreate()

		val dependency = Dependency.INSTANCE
		dependency.init {
			singelton<Backend>(BackendMock())
			singelton<DataBase>(DataBaseMock())
			singelton<BreakfastFactory>(BreakfastFactoryImpl())
		}
	}
}