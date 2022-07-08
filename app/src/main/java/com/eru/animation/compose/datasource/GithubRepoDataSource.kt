package com.eru.animation.compose.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eru.animation.compose.network.ApiException
import okio.IOException
import com.eru.animation.compose.models.github.GithubRepo
import com.eru.animation.compose.repositories.AppRepository
import retrofit2.HttpException

class GithubRepoDataSource(
    private val repository: AppRepository,
    private val query: String,
) : PagingSource<Int, GithubRepo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> {
        val pagePosition = params.key ?: 1

        return try {

            val response = repository.searchGithubRepo(
                page = pagePosition,
                sort = "stars",
                order = "desc",
                query = query,
            )

            val result = response.items

            if (result == null) {
                LoadResult.Error(ApiException(response.message ?: "No data returned!"))
            } else {
                val nextKey = if (result.isEmpty()) {
                    null
                } else {
                    pagePosition + 1
                }

                LoadResult.Page(
                    data = result,
                    prevKey = if (pagePosition == 1) null else pagePosition - 1,
                    nextKey = nextKey
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: ApiException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
