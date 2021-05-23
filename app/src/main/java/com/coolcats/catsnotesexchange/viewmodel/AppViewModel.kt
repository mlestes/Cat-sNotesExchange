package com.coolcats.catsnotesexchange.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coolcats.catsnotesexchange.mod.User
import com.coolcats.catsnotesexchange.util.Util
import com.coolcats.catsnotesexchange.util.UtilStatus

class AppViewModel : ViewModel() {

    val statusData = MutableLiveData<UtilStatus>()
    val createStatusData = MutableLiveData<Boolean>()
    val loginStatus = MutableLiveData<Boolean>()
    val userData = MutableLiveData<User>()

    fun getCurrentUser(){
        val user = Util.getLoggedInUser()
        user?.uid?.let { key ->
            Util.getUserDB().child(key).get().addOnSuccessListener { snap ->
                userData.value = snap.getValue(User::class.java)
            }.addOnFailureListener {
                Util.myLog(it.localizedMessage ?: "An Error has occurred in getLoggedInUserName()")
            }
        }

    }

}