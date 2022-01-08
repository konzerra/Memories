package com.konzerra.memories.domain.model

import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import com.konzerra.memories.data.dto.toTag
import io.realm.RealmList


data class Memory(
    val text: String,
    val tags: List<Tag>,
    val date: String,
    val id: String,
)
