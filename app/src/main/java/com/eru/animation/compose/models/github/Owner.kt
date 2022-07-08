package com.eru.animation.compose.models.github

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "login")
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)
