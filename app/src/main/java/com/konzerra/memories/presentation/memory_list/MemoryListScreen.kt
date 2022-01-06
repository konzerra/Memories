package com.konzerra.memories.presentation.memory_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.konzerra.memories.SharedViewModel
import com.konzerra.memories.presentation.common.top_bars.TopBarSearch
import com.konzerra.memories.presentation.common.top_bars.Triangle
import com.konzerra.memories.presentation.memory_list.common.MemoryListView
import com.konzerra.memories.presentation.navigation.Screen
import com.konzerra.memories.ui.theme.Black

@ExperimentalMaterialApi
@Composable
fun MemoryListScreen(
    openDrawer: (Unit) -> Unit,
    navController: NavController,
    viewModel: MemoryListViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
)
{
    val state = viewModel.state.value
    val constraints = setConstraints()
    Surface(color = Black){
        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxSize()
                .background(Black)

        ) {
            MemoryListView(
                modifier = Modifier.layoutId("memoryListView"),
                memoryList = state.memories,
                onButtonClick = {

                    navController.navigate(Screen.MemoryScreen.route+"/${it.id}")
                }
            )
            TopBarSearch(
                modifier = Modifier.layoutId("topBar"),
                searchTitle = "Search",
                onMenuClicked = {
                    openDrawer(Unit)
                },
                onSearchRequest = {

                }
            )
            Triangle(modifier = Modifier.layoutId("topTriangle"))
        }
}


}
//constraints
private fun setConstraints(): ConstraintSet {
    val constraints = ConstraintSet {
        val topBar = createRefFor("topBar")
        val topTriangle = createRefFor("topTriangle")
        val memoryListView = createRefFor("memoryListView")

        constrain(memoryListView) {
            top.linkTo(topBar.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
        constrain(topTriangle) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(topBar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    return constraints
}