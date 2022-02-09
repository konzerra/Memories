package com.konzerra.memories.domain.usecases

import androidx.compose.runtime.currentComposer
import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import com.konzerra.memories.data.dto.toMemory
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.domain.repository.MemoryRepository
import io.realm.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.*
import javax.inject.Inject

class WriteMemoryUseCase@Inject constructor(
    private val repository: MemoryRepository
) {
    suspend operator fun invoke(memory: MemoryDto, existingTagList: List<TagDto>){
        val date = Calendar.getInstance().time
        memory.date = date.toString()

        existingTagList.forEach { existingTag->
            memory.tags!!.removeIf {
                it.text == existingTag.text
            }.also {
                if(it){
                    memory.tags!!.add(existingTag)
                }
            }

        }
        repository.writeMemory(memory)
    }
}