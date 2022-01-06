package com.konzerra.memories.domain.usecases

import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.toMemory
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.repository.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMemoryListUseCase @Inject constructor(
    private val repository: MemoryRepository
) {
    operator fun invoke(): Flow<Resource<List<Memory>>> = flow {
        try {
            emit(Resource.Loading<List<Memory>>())
            val memoryList = repository.getMemoryList()
            emit(Resource.Success<List<Memory>>(memoryList.map {
                it.toMemory()
            }))
        } catch(e:Exception) {
            emit(Resource.Error<List<Memory>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Memory>>("Couldn't reach server. Check your internet connection."))
        }
    }
}