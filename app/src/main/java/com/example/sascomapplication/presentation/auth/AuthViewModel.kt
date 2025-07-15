package com.example.sascomapplication.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userId: String) : AuthState()
    data class Error(val message: String) : AuthState()
    object LoggedOut : AuthState()
}

class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val auth: FirebaseAuth
) : ViewModel(){
private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _authState.value = AuthState.Success(it.user?.uid ?: "")
                }
                .addOnFailureListener {
                    _authState.value = AuthState.Error(it.message ?: "Login failed")
                }
        }
    }

    fun register(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _authState.value = AuthState.Success(it.user?.uid ?: "")
                }
                .addOnFailureListener {
                    _authState.value = AuthState.Error(it.message ?: "Registration failed")
                }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.LoggedOut
    }

    fun checkAuthState() {
        val user = auth.currentUser
        _authState.value = if (user != null) AuthState.Success(user.uid) else AuthState.LoggedOut
    }
}
