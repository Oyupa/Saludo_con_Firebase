package com.example.saludo_con_firebase

import androidx.compose.ui.graphics.Color

data class User(
    val name: String,
    val color: Int = 0
) {
    constructor() : this("", 0)
}