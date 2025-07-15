package com.example.sascomapplication.domain.repository

import com.example.sascomapplication.data.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val currentUser: FirebaseUser?

    fun isUserLoggedIn(): Boolean

    suspend fun registerUser(email: String, password: String, name: String): Result<Unit>

    suspend fun loginUser(email: String, password: String): Result<Unit>

    fun logoutUser()

    suspend fun getUserData(uid: String): User?

    suspend fun saveUserData(uid: String, user: User): Result<Unit>

    fun observeAuthState(): Flow<FirebaseUser?>
}
