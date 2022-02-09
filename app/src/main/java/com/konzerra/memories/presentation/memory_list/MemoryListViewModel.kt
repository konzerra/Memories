package com.konzerra.memories.presentation.memory_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.common.Resource
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.domain.usecases.GetMemoryListByTagAndOrKeyUseCase
import com.konzerra.memories.domain.usecases.GetMemoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MemoryListViewModel  @Inject constructor(
    private val getMemoryListUseCase: GetMemoryListUseCase,
    private val getMemoryListByTagAndOrKeyUseCase: GetMemoryListByTagAndOrKeyUseCase,
) : ViewModel(){

    private val _defaultMemoryList = mutableStateOf<List<Memory>>(emptyList())
    private val _searchedMemoryList = mutableStateOf<List<Memory>>(emptyList())
    val defaultMemoryList : State<List<Memory>> = _defaultMemoryList
    val searchedMemoryList : State<List<Memory>> = _searchedMemoryList

    private val _searchText = mutableStateOf("")
    val searchText : State<String> = _searchText

    private val _tags = mutableStateOf<List<Tag>>(emptyList())
    val tags : State<List<Tag>> = _tags

    private val _newTagDialogState = mutableStateOf(false)
    val newTagDialogState:State<Boolean> = _newTagDialogState

    init{
        getMemoryList()
    }
    fun updateList(){
        getMemoryList()
    }
    fun pushNewTag(newTag:String){
        _tags.value = _tags.value.plus(Tag(newTag,"1"))
        if(_tags.value.isNotEmpty()){
            getMemoryListByTagAndOrKey()
        }

    }
    fun pullNewTag(tag: Tag){
        val list = _tags.value.toMutableList()
        list.removeIf {
            it.text == tag.text
        }
        _tags.value = list
        if(_tags.value.isNotEmpty()){
            getMemoryListByTagAndOrKey()
        }
    }
    fun setNewTagDialogState(){
        _newTagDialogState.value = !newTagDialogState.value
    }
    fun searchByKey(){
        if(_searchText.value.isNotBlank() || _tags.value.isNotEmpty()){
            getMemoryListByTagAndOrKey()
        }
    }
    fun setSearchText(searchText:String){
        _searchText.value = searchText
    }
    private fun getMemoryListByTagAndOrKey(){
        getMemoryListByTagAndOrKeyUseCase(_searchText.value, _tags.value).onEach { result->
            when(result){
                is Resource.Success->{
                    _searchedMemoryList.value =  result.data ?: emptyList()
                }
                is Resource.Error->{
                   // _state.value =  MemoryListState(error = result.message ?:
                   // "An unexpected error occurred")
                }
                is Resource.Loading->{
                   // _state.value =  MemoryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getMemoryList(){
        getMemoryListUseCase().onEach { result->
            when(result){
                is Resource.Success->{
                    _defaultMemoryList.value = result.data ?: emptyList()
                }
                is Resource.Error->{
                  //  _state.value =  MemoryListState(error = result.message ?:
                   // "An unexpected error occurred")
                }
                is Resource.Loading->{
                  //  _state.value =  MemoryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}