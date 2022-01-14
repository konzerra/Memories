package com.konzerra.memories.presentation.tag_list.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.presentation.common.MemoryTag

@Composable
fun TagListView(
    modifier: Modifier,
    tagList:List<Tag>,
    onTagClicked:(Tag)->Unit
){
    LazyColumn(modifier = modifier){
        itemsIndexed(
            tagList
        ){ index, item->
            MemoryTag(
                tag = item,
                onClicked = {
                    onTagClicked(it)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}