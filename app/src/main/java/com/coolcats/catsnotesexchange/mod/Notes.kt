package com.coolcats.catsnotesexchange.mod

data class Notes(val key: String, val subject: String, var author: String, val title: String, var body: String) {
    constructor() : this("", "", "", "", "")
}
