package com.konzerra.memories.presentation.tag_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
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
    viewModel: TagListViewModel = hiltViewModel(),
    openDrawer:(Unit)->Unit
) {
    val state = viewModel.state.value
    val constraints = setConstraints()
    Surface(color = Black) {
        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxSize()
                .background(Black)

        ) {
            TagListView(
                modifier = Modifier.layoutId("memoryListView"),
                tagList = state.tags,
                onTagClicked = {

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