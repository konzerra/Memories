package com.konzerra.memories.presentation.new_memory

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.TagDto
import com.konzerra.memories.data.dto.toTag
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.domain.usecases.GetTagListUseCase
import com.konzerra.memories.domain.usecases.WriteMemoryUseCase
import com.konzerra.memories.presentation.memory_list.MemoryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.RealmList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewMemoryViewModel @Inject constructor(
    private val writeMemoryUseCase: WriteMemoryUseCase,
    private val getTagListUseCase: GetTagListUseCase,
) : ViewModel(){
    private val _state = mutableStateOf(NewMemoryState())
    val state : State<NewMemoryState> = _state

    private val _memoryText = mutableStateOf("")
    val memoryText : State<String> = _memoryText

    private val _newTagList = mutableStateOf<List<Tag>>(emptyList())
    val newTagList : State<List<Tag>> = _newTagList

    private val _newTagDialogState = mutableStateOf(false)
    val newTagDialogState:State<Boolean> = _newTagDialogState

    private val _existingTagList = mutableStateOf<List<TagDto>>(RealmList())
    val existingTagList : State<List<Tag>> =  mutableStateOf(_existingTagList.value.map {
        it.toTag()
    })
    fun pushNewTag(tagText:String){
        getTagList()
        val newTag =  Tag(
            text= tagText,
            id = "0",
        )
        _newTagList.value = _newTagList.value.plus(newTag)
    }
    fun setNewTagDialogState(){
        _newTagDialogState.value = !newTagDialogState.value
    }
    fun setMemoryText(text:String){
        _memoryText.value = text
    }

    fun rememberMemory(){
        viewModelScope.launch{
            val realmList: RealmList<TagDto> = RealmList()
            _newTagList.value.forEach {
                realmList.add(TagDto(it.text))
            }
            val newMemory = MemoryDto(
                text = memoryText.value,
                date = "",
                tags = realmList,
            )
            writeMemoryUseCase.invoke(newMemory, _existingTagList.value)
        }
    }
    private fun getTagList(){
        getTagListUseCase().onEach { result->
            when(result){
                is Resource.Success->{
                    _existingTagList.value =result.data ?: emptyList()
                }
                is Resource.Error->{

                }
                is Resource.Loading->{

                }
            }
        }.launchIn(viewModelScope)
    }
}
