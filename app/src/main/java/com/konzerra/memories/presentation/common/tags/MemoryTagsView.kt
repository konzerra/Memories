package com.konzerra.memories.presentation.common.tags

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.presentation.common.MemoryTag

@Composable
fun MemoryTagsView(
    tags:List<Tag>,
    modifier:Modifier,
    onTagClicked: (Tag)->Unit
){
    LazyRow(modifier = modifier){
        item{
            MemoryTag(
                tag = Tag("+New", "-1"),
                onClicked = {
                    onTagClicked(it)
                }
            )
            Spacer(modifier = Modifier.width(4.dp).height(1.dp))
        }
        itemsIndexed(
           tags
        ){ index, item ->
            MemoryTag(
                tag = item,
                onClicked = {
                    onTagClicked(it)
                }
            )
            Spacer(modifier = Modifier.width(4.dp).height(1.dp))
        }

        
    }
}