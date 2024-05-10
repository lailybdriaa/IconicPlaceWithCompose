package com.example.iconicplacewithcompose.model

data class IconicPlace(
    val id: Long,
    val name: String,
    val description: String,
    val image: Int,
    val country : String,
    val city : String,
    val year : String,
)