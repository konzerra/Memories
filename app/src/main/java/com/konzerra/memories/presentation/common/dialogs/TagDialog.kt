package com.konzerra.memories.presentation.common.dialogs

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konzerra.memories.ui.theme.Purple

@Composable
fun NewTagDialog(
    openDialog:Boolean,
    onClicked: (String)->Unit,
    onCloseDialog: (Unit)->Unit
){

    val text = remember { mutableStateOf("") }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "Title")
            },
            text = {
                Column() {
                    OutlinedTextField(
                        value = text.value,
                        label = { Text("Tag") },
                        onValueChange = {
                            text.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                        ,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Purple,
                            unfocusedBorderColor = Purple)

                    )

                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                            .border(
                                width = 1.dp,
                                color = Purple,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .clickable {
                                onCloseDialog(Unit)
                            },

                    ) {
                        Text("Cancel")
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Purple,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .clickable {
                                if(text.value.isNotBlank()){
                                    onClicked(text.value)
                                }
                            },

                        ) {
                        Text("Ok")
                    }
                }
            }
        )
    }
}