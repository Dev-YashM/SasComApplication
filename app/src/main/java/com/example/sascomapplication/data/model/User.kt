package com.example.sascomapplication.data.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val addresses: List<String> = emptyList()
)
