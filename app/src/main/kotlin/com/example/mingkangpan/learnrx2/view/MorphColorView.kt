package com.example.mingkangpan.learnrx2.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.View
import io.reactivex.functions.Consumer
import java.util.*
import android.view.ViewAnimationUtils


/**
 * Created by mingkangpan on 17/04/2017.
 */
class MorphColorView(context: Context, attrs: AttributeSet?) : View(context, attrs), Consumer<Long>{

	override fun accept(t: Long?) {
		val rnd = Random()
		val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

		setBackgroundColor(color)

		if(Build.VERSION.SDK_INT >= 21) {
			ViewAnimationUtils
					.createCircularReveal(this, width/2, height/2, 0f, Math.max(width, height).toFloat())
					.start()
		}
	}
}