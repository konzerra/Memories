package com.konzerra.memories.presentation.navigation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.konzerra.memories.R
import com.konzerra.memories.ui.theme.Purple
import com.konzerra.memories.ui.theme.White


@Composable
fun TopBox(modifier: Modifier){
    val constraints = ConstraintSet {
        val textView = createRefFor("textView")
        val iconView = createRefFor("iconView")
        constrain(textView) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent

        }
        constrain(iconView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(textView.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    Card(modifier = modifier
        .padding(bottom = 0.dp)
        .fillMaxWidth()
        .height(56.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(0.dp)
    ) {
        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxSize()
                .background(Purple)
        ) {
            Text(
                text = "Memories",
                fontSize = 20.sp,
                color = White,
                modifier = Modifier.layoutId("textView")
            )
            Image(
                painterResource(R.drawable.ic_logo),
                "",
                modifier = Modifier
                    .layoutId("iconView")
                    .height(40.dp)
                    .width(40.dp)
                    .padding(end = 10.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.lighting(White, White)
            )


        }
    }


}