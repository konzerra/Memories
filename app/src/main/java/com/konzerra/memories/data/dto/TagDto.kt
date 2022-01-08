package com.konzerra.memories.data.dto

import com.konzerra.memories.domain.model.Tag
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class TagDto(
    var text:String = "",
    @PrimaryKey
    var id: String = ObjectId().toHexString(),
    @LinkingObjects("tags")
    val memory: RealmResults<MemoryDto>? = null
):RealmObject()

fun TagDto.toTag(): Tag{
    return Tag(
        id = id,
        text = text,
    )
}
