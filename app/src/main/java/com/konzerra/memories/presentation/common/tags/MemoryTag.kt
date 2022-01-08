package com.konzerra.memories.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White

@Composable
fun MemoryTag(
    tag: Tag,
    onClicked: (Tag)->Unit
){
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = Purple,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)
        .clickable {
            onClicked(tag)
        }
    ){
        Text(
            text = tag.text,
            color = White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}