package com.coolcats.catsnotesexchange.util

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.User
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.user_info_fragment_layout.*

class Util {

    companion object {
        fun myLog(msg: String) = Log.d("MY_TAG", msg)
        fun checkUserLogin(): Boolean = (FirebaseAuth.getInstance().currentUser != null)
        fun getLoggedInUser(): FirebaseUser? = FirebaseAuth.getInstance().currentUser
        fun showError(v: View, msg: String) {
            val snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(
                ContextCompat.getColor(
                    v.context,
                    R.color.pastel_red
                )
            )
            snackbar.show()
        }



        fun getUserDB(): DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
        fun getNotesDB(): DatabaseReference =
            FirebaseDatabase.getInstance().reference.child("Notes")
    }
}