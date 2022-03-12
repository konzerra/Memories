package com.konzerra.memories.presentation.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.konzerra.memories.presentation.common.buttons.ButtonBottom
import com.konzerra.memories.presentation.common.top_bars.TopBarSearch
import com.konzerra.memories.presentation.common.top_bars.TopBarText
import com.konzerra.memories.presentation.common.top_bars.Triangle
import com.konzerra.memories.presentation.help.HelpScreenViewModel
import com.konzerra.memories.presentation.help.components.HelpTipListView
import com.konzerra.memories.presentation.settings.components.SettingsItemsListView
import com.konzerra.memories.ui.theme.Black

@Composable
fun SettingsScreen(
    openDrawer: (Unit) -> Unit,
    viewModel: SettingsScreenViewModel,

){
    val constraints = remember {
        mutableStateOf(setConstraints())
    }
    DisposableEffect(key1 = viewModel) {

        Log.w("testingCompose", "onStart")
        onDispose {  }
    }
    Surface(color = Black){
        ConstraintLayout(
            constraints.value,
            modifier = Modifier
                .fillMaxSize()
                .background(Black)

        ) {

            SettingsItemsListView(

                modifier = Modifier.layoutId("settingsItemsListView")
                    .fillMaxSize()
            )
            TopBarText(
                modifier = Modifier.layoutId("topBar"),
                title = "Settings",
                onMenuClicked = {
                    openDrawer(Unit)
                },

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
        val helpTipListView = createRefFor("settingsItemsListView")
        constrain(helpTipListView) {
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