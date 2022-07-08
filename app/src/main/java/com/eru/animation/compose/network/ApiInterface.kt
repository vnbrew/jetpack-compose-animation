package com.eru.animation.compose.network

import com.eru.animation.compose.models.github.GithubRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    // ----------------------------------------------------------------
    // Search Github Repositories
    // Help: https://docs.github.com/en/rest/reference/search#search-repositories
    // ----------------------------------------------------------------
    @GET("https://api.github.com/search/repositories")
    suspend fun searchGithubRepo(
        @Query("page") page: Int,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("q") query: String,
    ): Response<GithubRepoResponse>
}
