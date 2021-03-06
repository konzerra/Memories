package com.konzerra.memories.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White
import com.konzerra.memories.R

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
        Row(){
            Text(
                text = tag.text,
                color = White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
        }

    }
}