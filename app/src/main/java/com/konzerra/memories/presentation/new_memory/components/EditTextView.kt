package com.konzerra.memories.presentation.new_memory.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konzerra.memories.ui.theme.Purple


@Composable
fun EditTextView(
    modifier : Modifier,
    text: String,
    onTextChange:(String) ->Unit
){

    OutlinedTextField(
        value = text,
        label = { Text("Write something") },
        onValueChange = {
            onTextChange(it)
            },
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Purple,
            unfocusedBorderColor = Purple)

    )
}