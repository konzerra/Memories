package com.konzerra.memories.domain.repository

import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.domain.model.Memory

interface MemoryRepository {
    suspend fun getMemory (memoryId:String): MemoryDto?
    suspend fun getMemoryList (): List<MemoryDto>
    suspend fun writeMemory(memory:MemoryDto)
    suspend fun updateMemory(memory: Memory)
}