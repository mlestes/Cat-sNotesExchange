package com.coolcats.catsnotesexchange.mod

data class User(val id: String, val userName: String) {
    constructor() : this("", "")
}
