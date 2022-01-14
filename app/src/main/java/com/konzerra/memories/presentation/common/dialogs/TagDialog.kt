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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.konzerra.memories.ui.theme.Black
import com.konzerra.memories.ui.theme.Purple

@Composable
fun NewTagDialog(
    openDialog:Boolean,
    onClicked: (String)->Unit,
    onCloseDialog: (Unit)->Unit
){

    val text = remember { mutableStateOf("") }

    if (openDialog) {
        Dialog(
            onDismissRequest = {

            },
            content = {
                Surface(color = Black) {
                    Column() {
                        OutlinedTextField(
                            value = text.value,
                            label = { Text("Tag") },
                            onValueChange = {
                                text.value = it
                            },
                            maxLines = 1,
                            modifier = Modifier
                                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                                .fillMaxWidth()
                            ,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Purple,
                                unfocusedBorderColor = Purple)

                        )
                        Row(
                            modifier = Modifier.padding(all = 0.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = Purple,
                                        shape = RoundedCornerShape(0.dp)
                                    )
                                    .clickable {
                                        onCloseDialog(Unit)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Cancel",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = Purple,
                                        shape = RoundedCornerShape(0.dp)
                                    )
                                    .clickable {
                                        if (text.value.isNotBlank()) {
                                            onClicked(text.value)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Ok",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }//end of the column
                }

            }


        )
    }
}