package com.konzerra.memories.presentation.new_memory

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.domain.model.toTagDto
import com.konzerra.memories.domain.usecases.WriteMemoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.RealmList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewMemoryViewModel @Inject constructor(
    private val writeMemoryUseCase: WriteMemoryUseCase
) : ViewModel(){
    private val _state = mutableStateOf(NewMemoryState())
    val state : State<NewMemoryState> = _state
    private val _memoryText = mutableStateOf("")
    val memoryText : State<String> = _memoryText
    private val _tagList = mutableStateOf<List<Tag>>(emptyList())
    val tagList : State<List<Tag>> = _tagList

    fun setTagList(){

    }
    fun setMemoryText(text:String){
        _memoryText.value = text
    }
    fun rememberMemory(){
        viewModelScope.launch{
            val realmList: RealmList<TagDto> = RealmList()
            _tagList.value.forEach {
                realmList.add(it.toTagDto())
            }
            val newMemory = MemoryDto(
                text = memoryText.value,
                date = "",
                tags = realmList,
            )
            writeMemoryUseCase.invoke(newMemory)
        }
    }
}
