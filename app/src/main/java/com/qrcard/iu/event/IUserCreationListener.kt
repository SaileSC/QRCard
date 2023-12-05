package com.qrcard.iu.event

interface UserCreationListener {
    fun onUserCreatedSuccessfully()
    fun onUserCreationFailed(errorMessage: String)
}