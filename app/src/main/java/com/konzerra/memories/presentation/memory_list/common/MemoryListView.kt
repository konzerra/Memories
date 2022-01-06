package com.konzerra.memories.presentation.memory_list.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.konzerra.memories.domain.model.Memory

@ExperimentalMaterialApi
@Composable
fun MemoryListView(
    memoryList: List<Memory>,
    onButtonClick: (Memory) -> Unit,
    modifier: Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        itemsIndexed(
            memoryList
        ){ index, memory->
            ExpandableMemoryCard(
                memory = memory
            ){
                onButtonClick(it)
            }
        }
    }
}