package com.konzerra.memories.domain.usecases

import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.toMemory
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.repository.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.*
import javax.inject.Inject

class WriteMemoryUseCase@Inject constructor(
    private val repository: MemoryRepository
) {
    suspend operator fun invoke(memory: MemoryDto){
        val date = Calendar.getInstance().time
        memory.date = date.toString()
        repository.writeMemory(memory)
    }
}