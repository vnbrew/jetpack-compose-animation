package com.eru.animation.compose.ui.screens.animation.index

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eru.animation.compose.ui.compositions.AppComponent.Header
import com.eru.animation.compose.ui.screens.AnimationsScreen
import com.eru.animation.compose.ui.theme.AppTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun AnimationIndexScreen(
    navigate: (AnimationsScreen) -> Unit,
) {
    AnimationIndexSkeleton(
        navigate = navigate
    )
}

@Preview
@Composable
fun AnimationIndexSkeletonPreview() {
    AppTheme {
        AnimationIndexSkeleton()
    }
}

@Composable
fun AnimationIndexSkeleton(
    navigate: (AnimationsScreen) -> Unit = {},
) {

    Scaffold(
        Modifier
            .navigationBarsWithImePadding()
            .statusBarsPadding()
    ) {

        Column(Modifier.fillMaxSize()) {
            Header("Compositions")

            LazyColumn(Modifier.fillMaxSize()) {

                itemsIndexed(Animation.animationList) { index, item ->

                    if (index != 0) {
                        Divider(Modifier.padding(16.dp, 0.dp))
                    }

                    Text(
                        modifier = Modifier
                            .clickable {
                                navigate(item.route)
                            }
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                end = 16.dp,
                                bottom = 8.dp
                            )
                            .fillMaxWidth(),
                        text = item.name
                    )
                }
            }
        }
    }
}
