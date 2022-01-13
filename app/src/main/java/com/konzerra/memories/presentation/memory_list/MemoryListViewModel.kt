package com.konzerra.memories.presentation.memory_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.common.Resource
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.usecases.GetMemoryListByKeyUseCase
import com.konzerra.memories.domain.usecases.GetMemoryListByTagUseCase
import com.konzerra.memories.domain.usecases.GetMemoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MemoryListViewModel @Inject constructor(
    private val getMemoryListUseCase: GetMemoryListUseCase,
    private val getMemoryListByKeyUseCase: GetMemoryListByKeyUseCase,
    private val getMemoryListByTagUseCase: GetMemoryListByTagUseCase
) : ViewModel(){

    private val _defaultMemoryList = mutableStateOf<List<Memory>>(emptyList())
    private val _searchedMemoryList = mutableStateOf<List<Memory>>(emptyList())
    val defaultMemoryList : State<List<Memory>> = _defaultMemoryList
    val searchedMemoryList : State<List<Memory>> = _searchedMemoryList

    private val _searchText = mutableStateOf("")
    val searchText : State<String> = _searchText


    init{
        getMemoryList()
    }
    fun searchByKey(){
        if(_searchText.value.isNotBlank()){
            getMemoryListByKey()
        }

    }
    fun setSearchText(searchText:String){
        _searchText.value = searchText
    }
    private fun getMemoryListByKey(){
        getMemoryListByKeyUseCase(_searchText.value).onEach { result->
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