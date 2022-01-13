package com.konzerra.memories.data.local

import android.util.Log
import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.domain.repository.LocalDataSourceRepository
import io.realm.Case
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject


class LocalDB @Inject constructor(
    private val realmConfiguration: RealmConfiguration
):LocalDataSourceRepository
{
    val realm: Realm = Realm.getInstance(realmConfiguration)

    override suspend fun getMemory(id:String): MemoryDto? {
        Log.d("testingId", id)
        return realm.where(MemoryDto::class.java)
            .equalTo("id", id).findFirst()
    }
    override suspend fun getMemoryList():List<MemoryDto>{
        return realm.where(MemoryDto::class.java).findAllAsync()
    }
    override suspend fun getTagList():List<TagDto>{
        return realm.where(TagDto::class.java).findAllAsync()
    }
    override suspend fun writeMemory(memory: MemoryDto){
        realm.executeTransactionAsync{ r->
            r.insertOrUpdate(memory)
        }
    }

    override suspend fun updateMemory(memory: Memory) {
        TODO("Not yet implemented")
    }

    override suspend fun getMemoryListByTag(tag:Tag):List<MemoryDto>{
        val query = realm.where(MemoryDto::class.java)
            .equalTo("tags.text", tag.text)
        return query.findAllAsync()
    }
    override suspend fun getMemoryListByKey(key:String): List<MemoryDto>{
        val query = realm.where(MemoryDto::class.java)
        return query.contains("text", key, Case.INSENSITIVE).findAllAsync()
    }
}