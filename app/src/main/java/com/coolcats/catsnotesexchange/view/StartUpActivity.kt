package com.coolcats.catsnotesexchange.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.util.Util.Companion.checkUserLogin
import com.coolcats.catsnotesexchange.util.Util.Companion.showError
import com.coolcats.catsnotesexchange.util.UtilStatus
import com.coolcats.catsnotesexchange.view.fragment.HomeFragment
import com.coolcats.catsnotesexchange.view.fragment.LoginFragment
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.activity_startup.*

class StartUpActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        viewModel.createStatusData.value = true
        viewModel.statusData.observe(this, {
            checkStatus(it)
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

    private fun checkStatus(status: UtilStatus?) {
        when(status){
            UtilStatus.LOADING -> progress_indicator.visibility = View.VISIBLE
            UtilStatus.SUCCESS -> progress_indicator.visibility = View.GONE
            else -> {
                progress_indicator.visibility = View.GONE
                showError(main_view, "An Error has occurred...")
            }
        }
    }
}