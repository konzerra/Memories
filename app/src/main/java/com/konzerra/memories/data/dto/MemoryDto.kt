package com.konzerra.memories.data.dto

import com.konzerra.memories.domain.model.Memory
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class MemoryDto(

    @PrimaryKey // 2.
    var id: String = ObjectId().toHexString(), // 3.
    var text: String = "",

    var tags: RealmList<TagDto>? = null,
    var date: String ="",

    ):RealmObject()

fun MemoryDto.toMemory(): Memory {
    return Memory(
        id = id,
        text =text,
        date = date,
        tags = tags?.map{
            it.toTag()
        } ?: emptyList()

    )
}