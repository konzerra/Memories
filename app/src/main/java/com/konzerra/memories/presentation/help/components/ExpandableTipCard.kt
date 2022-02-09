package com.konzerra.memories.presentation.help.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableTipCard(
    tip:String
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
                        text = tip,
                        //fontSize = titleFontSize,
                        //fontWeight = titleFontWeight,
                        maxLines = 6,
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

            }else{
                Column(modifier = Modifier.clickable {
                    expandedState = !expandedState
                }) {
                    Text(
                        text = tip,
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