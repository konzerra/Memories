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
import com.konzerra.memories.presentation.about_us.AboutUsScreen
import com.konzerra.memories.presentation.help.HelpScreen
import com.konzerra.memories.presentation.help.HelpScreenViewModel
import com.konzerra.memories.presentation.memory.MemoryScreen
import com.konzerra.memories.presentation.memory.MemoryScreenViewModel
import com.konzerra.memories.presentation.memory_list.MemoryListScreen
import com.konzerra.memories.presentation.memory_list.MemoryListViewModel
import com.konzerra.memories.presentation.new_memory.NewMemoryScreen
import com.konzerra.memories.presentation.new_memory.NewMemoryViewModel
import com.konzerra.memories.presentation.tag_list.TagListScreen
import com.konzerra.memories.presentation.tag_list.TagListViewModel

@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    openDrawer: (Unit) -> Unit,
    sharedViewModel: SharedViewModel = hiltViewModel(),
    memoryScreenViewModel: MemoryScreenViewModel = hiltViewModel(),
    memoryListViewModel: MemoryListViewModel = hiltViewModel(),
    newMemoryViewModel: NewMemoryViewModel = hiltViewModel(),
    tagListViewModel: TagListViewModel = hiltViewModel(),
    helpListScreenViewModel: HelpScreenViewModel = hiltViewModel()
){


    NavHost(
        navController = navController,
        startDestination =  navController.currentDestination?.route ?: Screen.NewMemoryScreen.route ,
    ){

        composable(
            route = Screen.NewMemoryScreen.route
        ){
            sharedViewModel.setCurrentScreen(Screen.NewMemoryScreen.route)
            NewMemoryScreen(
                openDrawer = openDrawer,
                viewModel = newMemoryViewModel,
            )
        }
        composable(
            route = Screen.MemoryListScreen.route
        ){
            sharedViewModel.setCurrentScreen(Screen.MemoryListScreen.route)
            MemoryListScreen(
                openDrawer = openDrawer,
                navController = navController,
                viewModel = memoryListViewModel,
                sharedViewModel = sharedViewModel,
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
                viewModel = memoryScreenViewModel,
                sharedViewModel = sharedViewModel,
                memoryId = entry.arguments?.getString("memoryId") ?: "",
            )
        }
        composable(
            route = Screen.TagListScreen.route,
        ){
            sharedViewModel.setCurrentScreen(Screen.TagListScreen.route)
            TagListScreen(
                openDrawer = openDrawer,
                viewModel = tagListViewModel
            )
        }
        composable(
            route = Screen.AboutUsScreen.route,
        ){
            sharedViewModel.setCurrentScreen(Screen.AboutUsScreen.route)
            AboutUsScreen(
                openDrawer = openDrawer,
            )
        }
        composable(
            route = Screen.HelpScreen.route,
        ){
            sharedViewModel.setCurrentScreen(Screen.HelpScreen.route)
            HelpScreen(
                openDrawer = openDrawer,
                viewModel = helpListScreenViewModel
            )
        }

    }
}