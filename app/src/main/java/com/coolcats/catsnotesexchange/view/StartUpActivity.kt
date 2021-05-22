package com.coolcats.catsnotesexchange.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.util.Util.Companion.checkUserLogin
import com.coolcats.catsnotesexchange.view.fragment.HomeFragment
import com.coolcats.catsnotesexchange.view.fragment.LoginFragment

class StartUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        val fragment = if (checkUserLogin()) HomeFragment() else LoginFragment()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.sign_up_frame, fragment)
            .commit()
    }
}