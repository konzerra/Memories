package com.konzerra.memories.presentation.memory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.konzerra.memories.SharedViewModel
import com.konzerra.memories.presentation.common.buttons.ButtonBottom
import com.konzerra.memories.presentation.common.tags.MemoryTagsView
import com.konzerra.memories.presentation.common.top_bars.TopBarText
import com.konzerra.memories.presentation.common.top_bars.Triangle
import com.konzerra.memories.ui.theme.Black

@Composable
fun MemoryScreen(
    openDrawer: (Unit) -> Unit,
    memoryId:String,
    viewModel: MemoryScreenViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
){
    viewModel.setMemory(memoryId)
    val state = viewModel.state.value
    val constraints = remember {
        mutableStateOf(setConstraints())
    }
    Surface(color = Black){
        state.memory?.let{
            ConstraintLayout(
                constraints.value,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)

            ) {
                Text(
                    text = "Tags",
                    modifier = Modifier
                        .layoutId("tvTags")
                        .padding(start = 16.dp)
                )
                MemoryTagsView(
                    tags = state.memory.tags,
                    modifier = Modifier
                        .layoutId("memoryTagsView")
                        .padding(start = 16.dp, top = 8.dp),
                    onTagClicked = {
                        //TODO
                    }
                )
                Text(
                    modifier = Modifier
                        .layoutId("tvDate")
                        .padding(start = 16.dp, bottom = 16.dp),
                    text = state.memory.date
                )
                LazyColumn(
                    modifier = Modifier
                        .layoutId("textView")
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .fillMaxSize()
                ){
                    item {
                        Text(modifier = Modifier,
                            text = state.memory.text
                        )
                    }
                }

                ButtonBottom(modifier = Modifier
                    .layoutId("buttonRemember"),
                    text = "Edit",
                    onClicked = {

                    })
                TopBarText(
                    modifier = Modifier.layoutId("topBar"),
                    title = "Memory",
                    onMenuClicked = {
                        openDrawer(Unit)
                    }
                )
                Triangle(modifier = Modifier.layoutId("topTriangle"))

            }
        } //if memory not null

        //create view for empty memory
    }


}
//constraints
private fun setConstraints(): ConstraintSet {
    val constraints = ConstraintSet {
        val topBar = createRefFor("topBar")
        val topTriangle = createRefFor("topTriangle")
        val textView = createRefFor("textView")
        val tvTags = createRefFor("tvTags")
        val memoryTagsView = createRefFor("memoryTagsView")
        val buttonRemember = createRefFor("buttonRemember")
        val tvDate = createRefFor("tvDate")
        constrain(textView) {
            top.linkTo(memoryTagsView.bottom)
            bottom.linkTo(buttonRemember.top)
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
            top.linkTo(tvTags.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
        constrain(buttonRemember) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(tvTags) {
            top.linkTo(topBar.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(tvDate){
            top.linkTo(textView.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(buttonRemember.top)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    return constraints
}