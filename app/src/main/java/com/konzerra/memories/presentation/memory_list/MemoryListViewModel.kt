package com.konzerra.memories.presentation.memory_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.common.Resource
import com.konzerra.memories.domain.usecases.GetMemoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MemoryListViewModel @Inject constructor(
    private val getMemoryListUseCase: GetMemoryListUseCase
) : ViewModel(){

    private val _state = mutableStateOf(MemoryListState())
    val state : State<MemoryListState> = _state
    init{
        getMemoryList()
    }
    private fun getMemoryList(){
        getMemoryListUseCase().onEach { result->
            when(result){
                is Resource.Success->{
                    _state.value = MemoryListState(memories = result.data ?: emptyList())
                }
                is Resource.Error->{
                    _state.value =  MemoryListState(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _state.value =  MemoryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}