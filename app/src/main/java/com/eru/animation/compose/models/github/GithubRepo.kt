package com.eru.animation.compose.models.github

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class GithubRepo(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "owner")
    val owner: Owner,
    @Json(name = "description")
    val description: String?,
    @Json(name = "stargazers_count")
    val stargazersCount: Int,
    @Json(name = "watchers_count")
    val watchersCount: Int,
    @Json(name = "forks_count")
    val forksCount: Int,
)
