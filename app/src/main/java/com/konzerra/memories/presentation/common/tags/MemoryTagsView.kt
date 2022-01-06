package com.konzerra.memories.presentation.common.tags

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.presentation.common.MemoryTag

@Composable
fun MemoryTagsView(
    tags:List<Tag>,
    modifier:Modifier
){
    LazyRow(modifier = modifier){
        itemsIndexed(
           tags
        ){ index, item ->
            MemoryTag(tag = item)
        }
        item{
            MemoryTag(tag = Tag("+New", "-1"))
        }
    }
}