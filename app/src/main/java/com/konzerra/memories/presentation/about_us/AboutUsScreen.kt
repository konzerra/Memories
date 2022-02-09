package com.konzerra.memories.presentation.about_us

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.konzerra.memories.presentation.common.top_bars.TopBarText
import com.konzerra.memories.presentation.common.top_bars.Triangle
import com.konzerra.memories.ui.theme.Black

@Composable
fun AboutUsScreen(
    openDrawer: (Unit) -> Unit
){
    val constraints = remember {
        mutableStateOf(setConstraints())
    }
    Surface(color = Black){
        ConstraintLayout(
            constraints.value
        ) {
            Text(
                text = "Konzerra group\n" +
                        "Kyrgyzstan, Bishkek",
                modifier = Modifier.layoutId("tvCompany")
            )
            Text(
                text = "Приложение подготовлено по заказу Фонда самосознания Руслана для персонального использования.\n" +
                        "\n" +
                        "Память является ядром личности. ",
                modifier = Modifier.layoutId("tvMain")
            )
            TopBarText(
                modifier = Modifier.layoutId("topBar"),
                title = "About us",
                onMenuClicked = {
                    openDrawer(Unit)
                }
            )
            Triangle(modifier = Modifier.layoutId("topTriangle"))
        }
    }
}
private fun setConstraints(): ConstraintSet {
    val constraints = ConstraintSet {
        val topBar = createRefFor("topBar")
        val topTriangle = createRefFor("topTriangle")
        val tvMain = createRefFor("tvMain")
        val tvCompany = createRefFor("tvCompany")
        constrain(tvMain) {
            top.linkTo(topBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
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

        constrain(tvCompany){
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    return constraints
}