package com.konzerra.memories.presentation.new_memory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.konzerra.memories.presentation.common.tags.MemoryTagsView
import com.konzerra.memories.presentation.common.buttons.ButtonBottom
import com.konzerra.memories.presentation.common.dialogs.NewTagDialog
import com.konzerra.memories.presentation.common.top_bars.TopBarText
import com.konzerra.memories.presentation.common.top_bars.Triangle
import com.konzerra.memories.presentation.new_memory.components.EditTextView
import com.konzerra.memories.ui.theme.Black


@Composable
fun NewMemoryScreen(
    openDrawer: (Unit) -> Unit,
    viewModel: NewMemoryViewModel = hiltViewModel(),
){

    val state = viewModel.state.value
    val constraints = setConstraints()
    Surface(color = Black){
        ConstraintLayout(
            constraints,
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
                tags = viewModel.newTagList.value,
                modifier = Modifier
                    .layoutId("memoryTagsView")
                    .padding(start = 16.dp, top = 8.dp),
                onTagClicked = {
                    if(it.id == "-1"){
                        viewModel.setNewTagDialogState()
                    }
                }
            )
            EditTextView(modifier = Modifier
                .layoutId("editTextView"),
                text = viewModel.memoryText.value,
                onTextChange = {
                    viewModel.setMemoryText(it)
                }
            )
            ButtonBottom(modifier = Modifier
                .layoutId("buttonRemember"),
                text = "Remember",
                onClicked = {
                    viewModel.rememberMemory()
                })
            TopBarText(
                modifier = Modifier.layoutId("topBar"),
                title = "New memory",
                onMenuClicked = {
                    openDrawer(Unit)
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
        val editTextView = createRefFor("editTextView")
        val tvTags = createRefFor("tvTags")
        val memoryTagsView = createRefFor("memoryTagsView")
        val buttonRemember = createRefFor("buttonRemember")
        constrain(editTextView) {
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

    }
    return constraints
}