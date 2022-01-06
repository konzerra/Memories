package com.konzerra.memories.presentation.navigation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.konzerra.memories.R
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White


@Composable
fun BottomBox(modifier: Modifier){
    val constraints = ConstraintSet {
        val iconView = createRefFor("iconView")
        constrain(iconView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end )
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    ConstraintLayout(
        constraints,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Purple)
    ) {
        Image(
            painterResource(R.drawable.ic_arrow),
            "",
            modifier = Modifier
                .layoutId("iconView")
                .height(40.dp)
                .width(40.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.lighting(White, White)
        )
    }
}