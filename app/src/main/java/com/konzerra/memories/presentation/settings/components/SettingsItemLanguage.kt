package com.konzerra.memories.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.konzerra.memories.ui.theme.Purple

@Composable
fun SettingsItemLanguage(){
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .clickable {

    })
    {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterStart,

            ) {
                Text(
                    text = "Language",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.CenterEnd,
                ) {
                Text(
                    text = "English",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Purple)
                .padding(top = 8.dp, bottom = 8.dp)
        )
    }
}