package com.konzerra.memories.domain.repository

import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.model.Tag

interface LocalDataSourceRepository {

    suspend fun getMemoryListByTagAndOrKey(key:String,tags:List<Tag>):List<MemoryDto>

    suspend fun getMemory (memoryId:String): MemoryDto?

    suspend fun getMemoryList (): List<MemoryDto>

    suspend fun getTagList():List<TagDto>

    suspend fun getTagListByKey(key:String):List<TagDto>

    suspend fun writeMemory(memory:MemoryDto)

    suspend fun updateMemory(memory: Memory)
}