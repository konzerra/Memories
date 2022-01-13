package com.konzerra.memories.presentation.navigation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konzerra.memories.presentation.navigation.Screen
import com.konzerra.memories.ui.theme.Black
import com.konzerra.memories.ui.theme.Purple


@Composable
fun DrawerItemList(
    modifier: Modifier,
    currentScreen: String,
    onItemClicked: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxSize(),
        elevation = 4.dp,
        shape = RoundedCornerShape(0.dp)
    ) {
        Box(modifier =  modifier
            .background(Black)
            .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .width(52.dp)
                    .padding(top = 4.dp)
                    .fillMaxHeight()
                    .border(BorderStroke(2.dp, Purple))

            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxSize()
            ) {
                item {
                    DrawerItem(
                        thisScreen = Screen.NewMemoryScreen,
                        currentScreen = currentScreen,
                        onClick = { screen ->
                            onItemClicked(screen)
                        }
                    )
                    DrawerItem(
                        thisScreen = Screen.MemoryListScreen,
                        currentScreen = currentScreen,
                        onClick = { screen ->
                            onItemClicked(screen)
                        }
                    )
                    DrawerItem(
                        thisScreen = Screen.TagListScreen,
                        currentScreen = currentScreen,
                        onClick = { screen ->
                            onItemClicked(screen)
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(start = 58.dp, end = 6.dp, bottom = 8.dp,)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Purple)
                    )
                    DrawerItem(
                        thisScreen = Screen.SettingsScreen,
                        currentScreen = currentScreen,
                        onClick = { screenRoute ->
                            onItemClicked(screenRoute)
                        }
                    )
                    DrawerItem(
                        thisScreen = Screen.AboutUsScreen,
                        currentScreen = currentScreen,
                        onClick = { screenRoute ->
                            onItemClicked(screenRoute)
                        }
                    )
                    DrawerItem(
                        thisScreen = Screen.HelpScreen,
                        currentScreen = currentScreen,
                        onClick = { screenRoute ->
                            onItemClicked(screenRoute)
                        }
                    )
                }
            }
        }
    }
}