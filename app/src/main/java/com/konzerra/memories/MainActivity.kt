package com.konzerra.memories

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.konzerra.memories.presentation.navigation.Drawer
import com.konzerra.memories.presentation.navigation.Navigation
import com.konzerra.memories.presentation.new_memory.NewMemoryScreen
import com.konzerra.memories.ui.theme.MemoriesTheme
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(this)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            MemoriesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        drawerContent = {
                            Drawer(
                                currentScreen = sharedViewModel.currentScreen.value,
                                onItemClicked = {  screenRoute ->
                                    navController.navigate(screenRoute)
                                    coroutineScope.launch {
                                        scaffoldState.drawerState.close()
                                    }
                                }
                            )
                        },
                    ) {
                        Navigation(
                            navController,
                            openDrawer = {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}