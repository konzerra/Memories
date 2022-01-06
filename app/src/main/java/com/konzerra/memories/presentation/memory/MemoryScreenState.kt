package com.konzerra.memories.presentation.memory

import com.konzerra.memories.domain.model.Memory

data class MemoryScreenState(
    val memory: Memory? = null,
    val isLoading :Boolean = false,
    val error : String = ""
)
