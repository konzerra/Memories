package com.konzerra.memories.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.konzerra.memories.presentation.navigation.components.AccountInfo
import com.konzerra.memories.presentation.navigation.components.BottomBox
import com.konzerra.memories.presentation.navigation.components.DrawerItemList
import com.konzerra.memories.presentation.navigation.components.TopBox
import com.konzerra.memories.ui.theme.White


@Composable
fun Drawer(
    currentScreen: String,
    onItemClicked: (String) -> Unit,
){
    val constraints = setConstraints()
    ConstraintLayout(constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        BottomBox(modifier = Modifier.layoutId("bottomBox"))
        DrawerItemList(modifier = Modifier.layoutId("drawerItemList"), currentScreen){ screen ->
            onItemClicked(screen)
        }
        AccountInfo(modifier = Modifier.layoutId("accountInfo"))
        TopBox(modifier = Modifier.layoutId("topBox"))
    }
}
private fun setConstraints():ConstraintSet{
    val constraints = ConstraintSet {
        val topBox = createRefFor("topBox")
        val bottomBox = createRefFor("bottomBox")
        val accountInfo = createRefFor("accountInfo")
        val drawerItemList = createRefFor("drawerItemList")

        constrain(topBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(accountInfo) {
            top.linkTo(topBox.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(drawerItemList) {
            top.linkTo(accountInfo.bottom)
            bottom.linkTo(bottomBox.top)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.fillToConstraints
        }
        constrain(bottomBox) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    return constraints
}