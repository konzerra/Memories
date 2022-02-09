package com.konzerra.memories.presentation.memory_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.konzerra.memories.SharedViewModel
import com.konzerra.memories.presentation.common.dialogs.NewTagDialog
import com.konzerra.memories.presentation.common.tags.MutableMemoryTagsView
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
    viewModel: MemoryListViewModel,
    sharedViewModel: SharedViewModel,
)
{
    viewModel.updateList()
    DisposableEffect(key1 = viewModel) {
        viewModel.updateList()
        Log.w("testingCompose", "onStart")
        onDispose {  }
    }

    var memoryList = viewModel.defaultMemoryList
    if(viewModel.searchText.value.isNotBlank() || viewModel.tags.value.isNotEmpty()){
        memoryList = viewModel.searchedMemoryList
    }
    val constraints = remember {
        mutableStateOf(setConstraints())
    }
    Surface(color = Black){
        ConstraintLayout(
            constraints.value,
            modifier = Modifier
                .fillMaxSize()
                .background(Black)

        ) {
            MutableMemoryTagsView(
                tags = viewModel.tags.value,
                modifier = Modifier
                    .layoutId("memoryTagsView")
                    .padding(start = 16.dp, top = 16.dp)
                ,
                onTagClicked = {
                    if(it.id == "-1"){
                        viewModel.setNewTagDialogState()
                    }
                },
                onTagCancelClicked = {
                    viewModel.pullNewTag(it)
                }
            )
            MemoryListView(
                modifier = Modifier.layoutId("memoryListView"),
                memoryList = memoryList.value,
                onButtonClick = {
                    navController.navigate(Screen.MemoryScreen.route+"/${it.id}")
                }
            )
            TopBarSearch(
                modifier = Modifier.layoutId("topBar"),
                searchTitle = "Search",
                searchText = viewModel.searchText.value,
                onMenuClicked = {
                    openDrawer(Unit)
                },
                onSearchRequest = {
                    viewModel.searchByKey()
                },
                onSearchTextChanged = {
                    viewModel.setSearchText(it)
                    if(viewModel.searchText.value.isNotBlank() || viewModel.tags.value.isNotEmpty()){
                        viewModel.searchByKey()
                    }
                }
            )
            Triangle(modifier = Modifier.layoutId("topTriangle"))
        }
        if(viewModel.newTagDialogState.value){
            NewTagDialog(
                openDialog = viewModel.newTagDialogState.value,
                onClicked = {
                    viewModel.pushNewTag(it)
                    viewModel.setNewTagDialogState()
                },
                onCloseDialog = {
                    viewModel.setNewTagDialogState()
                })
        }
    }
}
//constraints
private fun setConstraints(): ConstraintSet {
    val constraints = ConstraintSet {
        val topBar = createRefFor("topBar")
        val topTriangle = createRefFor("topTriangle")
        val memoryListView = createRefFor("memoryListView")
        val memoryTagsView = createRefFor("memoryTagsView")
        constrain(memoryListView) {
            top.linkTo(memoryTagsView.bottom)
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
        constrain(memoryTagsView) {
            top.linkTo(topBar.bottom)
            bottom.linkTo(memoryListView.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
    }
    return constraints
}