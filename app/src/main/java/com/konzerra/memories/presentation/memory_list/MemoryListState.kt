package com.konzerra.memories.presentation.memory_list

import com.konzerra.memories.domain.model.Memory

data class MemoryListState(
    val tags:List<String> = emptyList(),
    val memories:List<Memory> = emptyList(),
    val isLoading :Boolean = false,
    val error : String = ""
)