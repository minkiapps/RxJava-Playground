package com.example.mingkangpan.learnrx2

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.mingkangpan.learnrx2.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		if (Build.VERSION.SDK_INT >= 21) {
			window.statusBarColor = Color.TRANSPARENT
		}

		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)

		val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()

		nav_view.setNavigationItemSelectedListener(this)
		if(savedInstanceState == null) {
			switchFragment(KotlinPlayGroundFragment())
		}
	}

	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		val id = item.itemId

		if (id == R.id.action_settings) {
			return true
		}

		return super.onOptionsItemSelected(item)
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		// Handle navigation view item clicks here.

		when(item.itemId) {
			R.id.nav_kotlin_playground -> switchFragment(KotlinPlayGroundFragment())
			R.id.nav_java_playground -> switchFragment(JavaPlayGroundFragment())
			R.id.nav_operators_examples -> switchFragment(OperatorsFragment())
			R.id.nav_coldhot_obs -> switchFragment(ColdHotObservableFragment())
			R.id.nav_rx_streams -> switchFragment(StreamFragment())
			R.id.nav_rx_subject -> switchFragment(SubjectFragment())
			R.id.nav_text_increment -> switchFragment(NumberIncrementFragment())
			R.id.nav_form -> switchFragment(FormFragment())
			R.id.nav_search -> switchFragment(SearchFragment())
			R.id.nav_image_morph -> switchFragment(MorphImageFragment())
			R.id.nav_permissions -> switchFragment(PermissionFragment())
		}

		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}

	private fun switchFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction().replace(R.id.activity_main_fragment_container, fragment).commit()
	}
}
