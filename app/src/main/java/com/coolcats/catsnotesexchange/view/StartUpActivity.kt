package com.coolcats.catsnotesexchange.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.util.Util.Companion.checkUserLogin
import com.coolcats.catsnotesexchange.util.Util.Companion.myLog
import com.coolcats.catsnotesexchange.util.Util.Companion.showError
import com.coolcats.catsnotesexchange.util.UtilStatus
import com.coolcats.catsnotesexchange.view.fragment.HomeFragment
import com.coolcats.catsnotesexchange.view.fragment.LoginFragment
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_startup.*

class StartUpActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        viewModel.loginStatus.value = checkUserLogin()
        viewModel.createStatusData.value = true
        viewModel.statusData.observe(this, {
            checkStatus(it)
        })
        viewModel.loginStatus.observe(this, {
            showLogoutButton(it)
        })

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

    private fun showLogoutButton(status: Boolean?) {
        if (status == true) {
            logout_btn.visibility = View.VISIBLE
            logout_btn.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                viewModel.loginStatus.value = false
                val count = supportFragmentManager.backStackEntryCount
                for (i in 0..count) supportFragmentManager.popBackStack()
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    .replace(R.id.sign_up_frame, LoginFragment())
                    .commit()
            }
        } else
            logout_btn.visibility = View.INVISIBLE
    }

    private fun checkStatus(status: UtilStatus?) {
        when (status) {
            UtilStatus.LOADING -> progress_indicator.visibility = View.VISIBLE
            UtilStatus.SUCCESS -> progress_indicator.visibility = View.GONE
            else -> {
                progress_indicator.visibility = View.GONE
                showError(main_view, "An Error has occurred...")
            }
        }
    }
}