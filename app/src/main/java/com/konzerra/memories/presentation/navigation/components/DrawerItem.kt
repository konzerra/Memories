package com.konzerra.memories.presentation.navigation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.konzerra.memories.presentation.navigation.Screen
import com.konzerra.memories.ui.theme.Black
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.Typography
import com.konzerra.memories.ui.theme.White


@Composable
fun DrawerItem(
    thisScreen: Screen,
    currentScreen: String,
    onClick: (String) -> Unit
){

    val constraints = setConstraints()
    val borderStroke = BorderStroke(2.dp, Black)
    val activeBackground = if(currentScreen == thisScreen.route) Purple else Black

    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable {
                onClick(thisScreen.route)
            }
    ) {
        Box(modifier = Modifier
            .layoutId("itemBox")
            .padding(start = 8.dp, end = 8.dp,  bottom = 8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .border(borderStroke)
            .background(activeBackground),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(id = thisScreen.stringResource),
                style = Typography.body1,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }

        Image(
            painterResource(thisScreen.drawableResource),
            "",
            modifier = Modifier
                .layoutId("iconView")
                .padding(end = 16.dp, start = 16.dp)
                .height(20.dp)
                .width(20.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.lighting(White, White)
        )
    }
}
private fun setConstraints() : ConstraintSet{
    val constraints = ConstraintSet {
        val itemBox = createRefFor("itemBox")
        val iconView = createRefFor("iconView")
        constrain(itemBox) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(iconView.end)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
        constrain(iconView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    return constraints
}