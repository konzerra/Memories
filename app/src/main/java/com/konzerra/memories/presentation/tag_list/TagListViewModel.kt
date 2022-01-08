package com.konzerra.memories.presentation.tag_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konzerra.memories.common.Resource
import com.konzerra.memories.data.dto.toTag
import com.konzerra.memories.domain.usecases.GetTagListUseCase
import com.konzerra.memories.presentation.memory_list.MemoryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TagListViewModel @Inject constructor(
    private val getTagListUseCase: GetTagListUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(TagListState())
    val state : State<TagListState> = _state
    init{
        getTagList()
    }
    private fun getTagList(){
        getTagListUseCase().onEach { result->
            when(result){
                is Resource.Success->{
                    _state.value = TagListState(tags = result.data?.map {
                        it.toTag()
                    } ?: emptyList())
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