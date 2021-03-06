package com.eru.animation.compose.models

data class ListItem(
    val id: Int,
    val name: String,
    val image: String = "https://picsum.photos/seed/$id/128/128",
)
