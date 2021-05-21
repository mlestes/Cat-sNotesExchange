package com.coolcats.catsnotesexchange.mod

data class User(val id: String, val userName: String, val year: String) {
    constructor() : this("", "", "")
}
