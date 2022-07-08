package com.eru.animation.compose.ui.screens.animation.index

import com.eru.animation.compose.ui.screens.AnimationsScreen

data class Animation(
    val name: String,
    val route: AnimationsScreen,
) {
    companion object {
        val animationList = listOf(
            Animation(
                name = "Animated Text & Bubbles",
                route = AnimationsScreen.AnimationComposeOne,
            ),
            Animation(
                name = "Emudi",
                route = AnimationsScreen.AnimationEmudi,
            ),
            Animation(
                name = "Running Car",
                route = AnimationsScreen.AnimationRunningCar,
            ),
            Animation(
                name = "The Story",
                route = AnimationsScreen.AnimationTheStory,
            ),
        )
    }
}
