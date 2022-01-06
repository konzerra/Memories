package com.konzerra.memories.presentation.common.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White


@Composable
fun ButtonBottom(
    modifier: Modifier,
    text:String,
    onClicked:(Unit)->Unit
){
    Button(
        modifier = modifier
            .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple,
        ),
        onClick = {
            onClicked(Unit)
        }
    ) {
        Text(
            text = text,
            color = White,
        )
    }
}