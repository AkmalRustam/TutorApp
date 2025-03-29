package com.akmaldev.tutorapp.data.remote.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akmaldev.tutorapp.data.model.response.main.studentstatus.Data
import com.akmaldev.tutorapp.data.remote.service.main.MainService
import javax.inject.Inject

class StudentStatusPagingSource @Inject constructor(
    private val mainService: MainService,
    private val status: String
) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: 1
        return try {
            val response = mainService.getStudentsByStatus(status, page, params.loadSize)
            if (response.isSuccessful) {
                val studentsResponseData = response.body()
                if (studentsResponseData != null) {
                    LoadResult.Page(
                        data = studentsResponseData.data,
                        prevKey = studentsResponseData.pagination.prevPage,
                        nextKey = studentsResponseData.pagination.nextPage
                    )
                } else {
                    LoadResult.Error(Exception("Response body is null"))
                }
            } else {
                LoadResult.Error(Exception("Network error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}