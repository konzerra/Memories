package com.konzerra.memories.domain.model

import com.konzerra.memories.data.dto.TagDto

data class Tag(
    val text:String,
    val id:String,
)
fun Tag.toTagDto(): TagDto{
    return TagDto(
        id = id,
        text = text,
    )
}
