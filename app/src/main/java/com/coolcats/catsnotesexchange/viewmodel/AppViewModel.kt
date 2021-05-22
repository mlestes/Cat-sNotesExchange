package com.coolcats.catsnotesexchange.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coolcats.catsnotesexchange.util.UtilStatus

class AppViewModel : ViewModel() {

    val statusData = MutableLiveData<UtilStatus>()
    val createStatusData = MutableLiveData<Boolean>()

}