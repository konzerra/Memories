package com.konzerra.memories.data.local

import android.util.Log
import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject


class LocalDB @Inject constructor(
    private val realmConfiguration: RealmConfiguration
) {
    val realm: Realm = Realm.getInstance(realmConfiguration)

    suspend fun getMemory(id:String): MemoryDto? {
        Log.d("testingId", id)
        return realm.where(MemoryDto::class.java)
            .equalTo("id", id).findFirst()
    }
    suspend fun getMemoryList():List<MemoryDto>{
        return realm.where(MemoryDto::class.java).findAllAsync()
    }
    suspend fun getTagList():List<TagDto>{
        return realm.where(TagDto::class.java).findAllAsync()
    }
    suspend fun writeMemory(memory: MemoryDto){
        realm.executeTransactionAsync{ r->
            r.insertOrUpdate(memory)
        }
    }
}