package com.konzerra.memories.domain.usecases

import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.toMemory
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.repository.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMemoryUseCase @Inject constructor(
    private val repository:MemoryRepository
) {
    operator fun invoke(memoryId: String): Flow<Resource<Memory>> = flow {
        try {
            emit(Resource.Loading<Memory>())
            val memory = repository.getMemory(memoryId)
            if (memory != null) {
                emit(Resource.Success<Memory>(memory.toMemory()))
            }
        } catch(e:Exception) {
            emit(Resource.Error<Memory>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<Memory>("Couldn't reach server. Check your internet connection."))
        }
    }
}