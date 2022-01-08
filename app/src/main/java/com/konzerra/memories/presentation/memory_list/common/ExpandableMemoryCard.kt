package com.konzerra.memories.presentation.memory_list.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.konzerra.memories.domain.model.Memory
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White

@ExperimentalMaterialApi
@Composable
fun ExpandableMemoryCard(
    memory:Memory,
    onButtonClick: (Memory) -> Unit
){

    var expandedState by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),

        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            if (expandedState) {
                Column() {
                    Text(
                        modifier = Modifier,
                        text = memory.text,
                        //fontSize = titleFontSize,
                        //fontWeight = titleFontWeight,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(start = 4.dp),
                            text = memory.date,
                            style = MaterialTheme.typography.body2,
                            maxLines = 6,
                            overflow = TextOverflow.Ellipsis
                        )
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Purple,
                            ),
                            onClick = {
                                onButtonClick(memory)
                            }
                        ) {
                            Text(
                                text = "Open",
                                color = White,
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

            }else{
                Column(modifier = Modifier.clickable {
                    expandedState = !expandedState
                }) {
                    Text(
                        text = memory.text,
                        //fontSize = descriptionFontSize,
                        //fontWeight = descriptionFontWeight,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Purple)
                            .padding(top = 8.dp, bottom = 8.dp)
                    )
                }

            }


        }
    }
}