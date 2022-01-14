package com.konzerra.memories.presentation.tag_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.toTag
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.domain.usecases.GetTagListByKeyUseCase
import com.konzerra.memories.domain.usecases.GetTagListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TagListViewModel @Inject constructor(
    private val getTagListUseCase: GetTagListUseCase,
    private val getTagListByKeyUseCase: GetTagListByKeyUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TagListState())
    val state : State<TagListState> = _state
    private val _searchText = mutableStateOf("")
    val searchText : State<String> = _searchText
    private val _defaultTagList = mutableStateOf<List<Tag>>(emptyList())
    private val _searchedTagList = mutableStateOf<List<Tag>>(emptyList())
    val defaultTagList : State<List<Tag>> = _defaultTagList
    val searchedTagList : State<List<Tag>> = _searchedTagList

    init{
        getTagList()
    }
    fun searchByKey(){
        if(_searchText.value.isNotBlank()){
            getTagListByKey()
        }

    }
    fun setSearchText(searchText:String){
        _searchText.value = searchText
    }
    private fun getTagListByKey(){
        getTagListByKeyUseCase(_searchText.value).onEach { result->
            when(result){
                is Resource.Success->{
                    _searchedTagList.value =  result.data ?: emptyList()
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
    private fun getTagList(){
        getTagListUseCase().onEach { result->
            when(result){
                is Resource.Success->{
                    _defaultTagList.value = result.data?.map {
                        it.toTag()
                    } ?: emptyList()
                }
                is Resource.Error->{
                    _state.value =  TagListState(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _state.value =  TagListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}