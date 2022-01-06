package com.konzerra.memories.data.dto

import com.konzerra.memories.domain.model.Tag
import io.realm.RealmObject
import org.bson.types.ObjectId

open class TagDto(
    var text:String = "",
    var id: String = ObjectId().toHexString(),
):RealmObject()

fun TagDto.toTag(): Tag{
    return Tag(
        id = id,
        text = text,
    )
}
