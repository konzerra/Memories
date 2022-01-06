package com.konzerra.memories.presentation.navigation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
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
import com.konzerra.memories.ui.theme.Black
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White
import com.konzerra.memories.R


@Composable
fun AccountInfo(modifier: Modifier){
    Surface(
        modifier = modifier,
        color = Black) {
        Box(modifier = Modifier
                .padding(top = 4.dp, end = 8.dp, start = 8.dp, bottom = 4.dp)
                .fillMaxWidth()
                .height(86.dp)
                .background(Black)
                .border(
                    width = 2.dp,
                    color = Purple,
                    shape = RoundedCornerShape(20.dp)
                ),
            ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ){

                Image(
                    painterResource(R.drawable.ic_account),
                    "",
                    modifier = Modifier
                        .layoutId("iconView")
                        .padding(end = 10.dp, start = 26.dp)
                        .height(40.dp)
                        .width(40.dp),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.lighting(White, White)
                )
                Text(
                    text = "Guest",
                    fontSize = 18.sp,
                    color = White,
                    modifier = Modifier.layoutId("textView")
                )
            }
        }
    }

}