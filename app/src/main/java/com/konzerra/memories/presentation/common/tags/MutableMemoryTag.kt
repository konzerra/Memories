package com.konzerra.memories.presentation.common.tags

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konzerra.memories.R
import com.konzerra.memories.domain.model.Tag
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White

@Composable
fun MutableMemoryTag(
    tag: Tag,
    onClicked: (Tag)->Unit,
    onCancelClicked:(Tag)->Unit,
){
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = Purple,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)

    ){
        Row(){
            Text(
                text = tag.text,
                color = White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.clickable {
                    onClicked(tag)
                }
            )
            Image(
                painterResource(R.drawable.ic_close),
                "",
                modifier = Modifier
                    .padding(end = 0.dp, start = 3.dp)
                    .height(20.dp)
                    .width(20.dp)
                    .clickable {
                        onCancelClicked(tag)
                    }
                ,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.lighting(White, White)
            )
        }

    }
}