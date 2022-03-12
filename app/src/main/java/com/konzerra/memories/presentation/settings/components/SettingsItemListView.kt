package com.konzerra.memories.presentation.settings.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.konzerra.memories.presentation.help.components.ExpandableTipCard

@Composable
fun SettingsItemsListView(
    modifier: Modifier
){
    LazyColumn(
        modifier = modifier
    ){
        item{
            SettingsItemLanguage()
        }
    }
}