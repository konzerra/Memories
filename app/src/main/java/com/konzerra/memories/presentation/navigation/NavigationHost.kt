package com.konzerra.memories.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.konzerra.memories.SharedViewModel
import com.konzerra.memories.presentation.memory.MemoryScreen
import com.konzerra.memories.presentation.memory_list.MemoryListScreen
import com.konzerra.memories.presentation.new_memory.NewMemoryScreen
import com.konzerra.memories.presentation.tag_list.TagListScreen

@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    openDrawer: (Unit) -> Unit
){
    val sharedViewModel: SharedViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination =  navController.currentDestination?.route ?: Screen.NewMemoryScreen.route ,
    ){


        composable(
            route = Screen.NewMemoryScreen.route
        ){
            sharedViewModel.setCurrentScreen(Screen.NewMemoryScreen.route)
            NewMemoryScreen(
                openDrawer = openDrawer
            )
        }
        composable(
            route = Screen.MemoryListScreen.route
        ){
            sharedViewModel.setCurrentScreen(Screen.MemoryListScreen.route)
            MemoryListScreen(
                openDrawer = openDrawer,
                navController = navController,
            )
        }
        composable(
            route = Screen.MemoryScreen.route+"/{memoryId}",
            arguments = listOf(
                navArgument(name = "memoryId"){
                    type = NavType.StringType
                    defaultValue = "Nothing"
                }
            )
        ){ entry->

            MemoryScreen(
                openDrawer = openDrawer,
                memoryId = entry.arguments?.getString("memoryId") ?: "",
            )
        }
        composable(
            route = Screen.TagListScreen.route,
        ){
            TagListScreen(
                openDrawer = openDrawer,
            )
        }

    }
}