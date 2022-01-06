package com.konzerra.memories.presentation.new_memory

import com.konzerra.memories.domain.model.Tag

data class NewMemoryState(
    val text:String? = null,
    val tags:List<Tag> = emptyList()
)
