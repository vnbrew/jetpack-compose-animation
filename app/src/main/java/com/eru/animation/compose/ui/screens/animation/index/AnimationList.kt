package com.eru.animation.compose.ui.screens.animation.index

import com.eru.animation.compose.ui.screens.AnimationsScreen

data class Animation(
    val name: String,
    val route: AnimationsScreen,
) {
    companion object {
        val animationList = listOf(
            Animation(
                name = "Example 1",
                route = AnimationsScreen.AnimationUsingAnimatedVisibility,
            ),
            Animation(
                name = "Example 2",
                route = AnimationsScreen.AnimationUsingAnimatedContent,
            ),
            Animation(
                name = "Example 3",
                route = AnimationsScreen.AnimationComposeOne,
            ),
            Animation(
                name = "Example 4",
                route = AnimationsScreen.AnimationEmudi,
            ),
            Animation(
                name = "Example 5",
                route = AnimationsScreen.AnimationRunningCar,
            ),
            Animation(
                name = "Example 6",
                route = AnimationsScreen.AnimationTheStory,
            ),
        )
    }
}
