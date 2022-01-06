package com.konzerra.memories.presentation.memory

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.MemoryDto
import com.konzerra.memories.data.dto.toMemory
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.domain.usecases.GetMemoryUseCase
import com.konzerra.memories.presentation.memory_list.MemoryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.RealmList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MemoryScreenViewModel @Inject constructor(
    private val getMemoryUseCase: GetMemoryUseCase
) : ViewModel(){

    private val _state = mutableStateOf(MemoryScreenState())
    val state : State<MemoryScreenState> = _state

    fun setMemory(memoryId:String){
        getMemoryUseCase(memoryId).onEach { result->
            when(result){
                is Resource.Success->{
                    _state.value = MemoryScreenState(memory = result.data)
                }
                is Resource.Error->{
                    _state.value =  MemoryScreenState(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _state.value =  MemoryScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}