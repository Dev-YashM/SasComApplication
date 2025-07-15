package com.example.sascomapplication.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val address: String = ""
)

class ProfileViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("users")

    init {
        loadUserData()
    }

    private fun loadUserData() {
        val currentUser = auth.currentUser ?: return

        viewModelScope.launch {
            database.child(currentUser.uid).get().addOnSuccessListener { snapshot ->
                val name = snapshot.child("name").getValue(String::class.java) ?: ""
                val email = currentUser.email ?: ""
                val address = snapshot.child("address").getValue(String::class.java) ?: ""

                _userProfile.value = UserProfile(
                    uid = currentUser.uid,
                    name = name,
                    email = email,
                    address = address
                )
            }
        }
    }

    fun logout() {
        auth.signOut()
    }
}
