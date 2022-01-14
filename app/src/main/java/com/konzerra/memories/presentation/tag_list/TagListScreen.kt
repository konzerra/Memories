package com.konzerra.memories.presentation.tag_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.konzerra.memories.presentation.common.top_bars.TopBarSearch
import com.konzerra.memories.presentation.common.top_bars.Triangle
import com.konzerra.memories.presentation.tag_list.components.TagListView
import com.konzerra.memories.ui.theme.Black

@Composable
fun TagListScreen(
    viewModel: TagListViewModel,
    openDrawer:(Unit)->Unit
) {
    var tagList = viewModel.defaultTagList
    if(viewModel.searchText.value.isNotBlank()){
        tagList = viewModel.searchedTagList
    }
    val constraints = remember {
        mutableStateOf(setConstraints())
    }
    Surface(color = Black) {
        ConstraintLayout(
            constraints.value,
            modifier = Modifier
                .fillMaxSize()
                .background(Black)

        ) {
            TagListView(
                modifier = Modifier
                    .layoutId("memoryListView")
                    .padding(16.dp),
                tagList = tagList.value,
                onTagClicked = {

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
                    if(viewModel.searchText.value.isNotBlank()){
                        viewModel.searchByKey()
                    }
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