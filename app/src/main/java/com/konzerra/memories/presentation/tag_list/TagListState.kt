package com.konzerra.memories.presentation.tag_list

import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.model.Tag

data class TagListState (
    val tags:List<Tag> = emptyList(),
    val isLoading :Boolean = false,
    val error : String = ""
)