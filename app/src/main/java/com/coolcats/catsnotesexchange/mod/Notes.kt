package com.coolcats.catsnotesexchange.mod

data class Notes(val subject: String, val author: String, val title: String, val body: String) {
    constructor() : this("", "", "", "")
}
