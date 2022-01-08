package com.konzerra.memories.data.repository

import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import com.konzerra.memories.data.local.LocalDB
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.repository.MemoryRepository
import javax.inject.Inject

class MemoryRepositoryImpl @Inject constructor(
    private val localDB: LocalDB
    ):MemoryRepository
{
    override suspend fun getMemory(memoryId: String): MemoryDto? {
        return localDB.getMemory(memoryId)
    }
    override suspend fun getMemoryList(): List<MemoryDto> {
        return localDB.getMemoryList()
    }

    override suspend fun getTagList(): List<TagDto> {
        return localDB.getTagList()
    }

    override suspend fun writeMemory(memory: MemoryDto) {
        localDB.writeMemory(memory)
    }

    override suspend fun updateMemory(memory: Memory) {
        TODO("Not yet implemented")
    }

}