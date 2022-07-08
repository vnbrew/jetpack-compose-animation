package com.eru.animation.compose.repositories

import android.content.Context
import com.eru.animation.compose.network.ApiInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.eru.animation.compose.network.SafeApiRequest
import javax.inject.Inject

class AppRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: ApiInterface
) {
    // ----------------------------------------------------------------
    // Search Github Repositories
    // ----------------------------------------------------------------

    suspend fun searchGithubRepo(
        page: Int,
        sort: String,
        order: String,
        query: String,
    ) = withContext(Dispatchers.IO) {

        SafeApiRequest.apiRequest(context) {
            api.searchGithubRepo(
                page = page,
                sort = sort,
                order = order,
                query = query,
            )
        }
    }
}
