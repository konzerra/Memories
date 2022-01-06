package com.konzerra.memories.presentation.common.top_bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.konzerra.memories.R
import com.konzerra.memories.ui.theme.*

@Composable
fun TopBarSearch(
    searchTitle:String,
    onMenuClicked:(Unit) -> Unit,
    onSearchRequest:(String) -> Unit,
    modifier: Modifier
) {
    var textFieldState by remember {
        mutableStateOf("")
    }
    val constraints = setConstraints()

    Card(modifier = modifier
        .height(56.dp),
        elevation =  4.dp,
        shape = RoundedCornerShape(0.dp),
    ) {
        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Black)
        ) {
            Image(
                painterResource(R.drawable.ic_menu),
                "",
                modifier = Modifier
                    .layoutId("iconMenuView")
                    .padding(end = 16.dp, start = 16.dp)
                    .height(25.dp)
                    .width(25.dp)
                    .clickable {
                        onMenuClicked(Unit)
                    },
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.lighting(White, White)
            )
            TextField(
                modifier = Modifier
                    .layoutId("editTextView")
                    .padding(end = 5.dp)
                ,
                value = textFieldState,
                label = { Text(searchTitle, color = White) },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = White,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                onValueChange = {
                    textFieldState=it
                },
            )
            Image(
                painterResource(R.drawable.ic_search),
                "",
                modifier = Modifier
                    .layoutId("iconSearchView")
                    .padding(end = 100.dp,)
                    .height(25.dp)
                    .width(25.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.lighting(White, White)
            )
        }
    }
}
private fun setConstraints():ConstraintSet {
    val constraints = ConstraintSet {
        val editTextView = createRefFor("editTextView")
        val iconMenuView = createRefFor("iconMenuView")
        val iconSearchView = createRefFor("iconSearchView")
        constrain(editTextView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(iconMenuView.end)
            end.linkTo(iconSearchView.start)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
        constrain(iconMenuView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(iconSearchView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    return constraints
}