package com.coolcats.catsnotesexchange.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.util.Util.Companion.myLog
import com.google.firebase.auth.FirebaseAuth

class StartUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        if(checkSignIn())
            myLog("Logged In")
         else
            myLog("Logged out")
    }

    private fun checkSignIn(): Boolean {
        return (FirebaseAuth.getInstance().currentUser != null)
    }

}