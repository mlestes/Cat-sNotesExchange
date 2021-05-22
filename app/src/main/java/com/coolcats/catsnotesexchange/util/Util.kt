package com.coolcats.catsnotesexchange.util

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Util {

    companion object {
        fun myLog(msg: String) = Log.d("MY_TAG", msg)
        fun checkUserLogin(): Boolean = (FirebaseAuth.getInstance().currentUser != null)
        fun getLoggedInUser(): FirebaseUser? =  FirebaseAuth.getInstance().currentUser
    }
}