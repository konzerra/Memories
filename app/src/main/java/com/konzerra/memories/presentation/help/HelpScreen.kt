package com.konzerra.memories.presentation.help

import android.util.Log
import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringArrayResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.konzerra.memories.R
import com.konzerra.memories.presentation.common.buttons.ButtonBottom
import com.konzerra.memories.presentation.common.top_bars.TopBarSearch
import com.konzerra.memories.presentation.common.top_bars.Triangle
import com.konzerra.memories.presentation.help.components.HelpTipListView
import com.konzerra.memories.ui.theme.Black

@Composable
fun HelpScreen(
    openDrawer: (Unit) -> Unit,
    viewModel:HelpScreenViewModel,
    tips:List<String>
){
    val constraints = remember {
        mutableStateOf(setConstraints())
    }
    DisposableEffect(key1 = viewModel) {
        viewModel.initTips(tips)
        Log.w("testingCompose", "onStart")
        onDispose {  }
    }

    var tipList =viewModel.tips
    if(viewModel.searchText.value.isNotBlank()){
        tipList = viewModel.searchedTips
    }
    Surface(color = Black){
        ConstraintLayout(
            constraints.value,
            modifier = Modifier
                .fillMaxSize()
                .background(Black)

        ) {
            ButtonBottom(
                text = "Understandable",
                modifier = Modifier.layoutId("button"),
                onClicked = {

                }
            )
            HelpTipListView(
                tips = tipList.value,
                modifier = Modifier.layoutId("helpTipListView")
                    .fillMaxSize()
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
        val helpTipListView = createRefFor("helpTipListView")
        val button = createRefFor("button")
        constrain(helpTipListView) {
            top.linkTo(topBar.bottom)
            bottom.linkTo(button.top)
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
        constrain(button) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
    }
    return constraints
}