package com.konzerra.memories.presentation.help.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HelpTipListView(
    tips:List<String>,
    modifier:Modifier
){
    LazyColumn(
        modifier = modifier
    ){
        itemsIndexed(
            tips
        ) { index, tip->
            ExpandableTipCard(tip = tip)
        }

    }
}