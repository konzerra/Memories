package com.konzerra.memories.presentation.common.top_bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konzerra.memories.R
import com.konzerra.memories.ui.theme.*

@Composable
fun TopBarText(
    title:String,
    onMenuClicked:(Unit) -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Black),
    ){
        Row(modifier = Modifier.fillMaxHeight(),) {
            Image(
                painterResource(R.drawable.ic_menu),
                "",
                modifier = Modifier
                    .layoutId("iconMenuView")
                    .padding(end = 16.dp, start = 16.dp, top = 16.dp)
                    .height(25.dp)
                    .width(25.dp)
                    .clickable {
                        onMenuClicked(Unit)
                    },
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.lighting(White, White)
            )
            Text(text = title,
                fontSize = 20.sp,
                color = White,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    }
}