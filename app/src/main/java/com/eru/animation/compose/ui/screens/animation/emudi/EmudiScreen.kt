package com.eru.animation.compose.ui.screens.animation.emudi

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eru.animation.compose.R
import com.eru.animation.compose.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmudiScreen() {
    val animState = MutableStateFlow(false)
    val animStateDot1 = MutableStateFlow(false)
    val animStateDot2 = MutableStateFlow(false)
    val animStateDot3 = MutableStateFlow(false)
    val animStateDot4 = MutableStateFlow(false)

    LaunchedEffect(Unit) {
        while (true) {
            delay(300)

            animState.value = true

            delay(1200)

            animStateDot1.value = true
            delay(200)
            animStateDot2.value = true
            delay(200)
            animStateDot3.value = true
            delay(200)
            animStateDot4.value = true

            delay(2200)

            animState.value = false
            animStateDot1.value = false
            animStateDot2.value = false
            animStateDot3.value = false
            animStateDot4.value = false

            delay(1200)
        }
    }

    EmudiScreenSkeleton(
        animState,
        animStateDot1,
        animStateDot2,
        animStateDot3,
        animStateDot4,
    )
}

@Preview
@Composable
fun EmudiScreenSkeletonPreview() {
    AppTheme {
        EmudiScreenSkeleton(
            MutableStateFlow(true),
            MutableStateFlow(true),
            MutableStateFlow(true),
            MutableStateFlow(true),
            MutableStateFlow(true),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmudiScreenSkeletonPreviewDark() {
    AppTheme {
        EmudiScreenSkeleton(
            MutableStateFlow(true),
            MutableStateFlow(true),
            MutableStateFlow(true),
            MutableStateFlow(true),
            MutableStateFlow(true),
        )
    }
}

@Composable
fun EmudiScreenSkeleton(
    _animState: StateFlow<Boolean>,
    _animStateDot1: StateFlow<Boolean>,
    _animStateDot2: StateFlow<Boolean>,
    _animStateDot3: StateFlow<Boolean>,
    _animStateDot4: StateFlow<Boolean>,
) {
    val animState = _animState.collectAsState()
    val animStateDot1 = _animStateDot1.collectAsState()
    val animStateDot2 = _animStateDot2.collectAsState()
    val animStateDot3 = _animStateDot3.collectAsState()
    val animStateDot4 = _animStateDot4.collectAsState()

    val updateTransition = updateTransition(
        targetState = animState.value,
        label = null
    )

    val updateTransitionDot1 = updateTransition(
        targetState = animStateDot1.value,
        label = null
    )

    val updateTransitionDot2 = updateTransition(
        targetState = animStateDot2.value,
        label = null
    )

    val updateTransitionDot3 = updateTransition(
        targetState = animStateDot3.value,
        label = null
    )

    val updateTransitionDot4 = updateTransition(
        targetState = animStateDot4.value,
        label = null
    )

    val textFadeInAlpha by updateTransition.animateFloat(
        label = "textFadeInAlpha",
        transitionSpec = {
            tween(
                durationMillis = 1000,
            )
        },
    ) { state ->
        when (state) {
            true -> 1f
            else -> 0f
        }
    }

    val iconSlideFromLeftTranslate by updateTransition.animateFloat(
        label = "iconSlideFromLeftTranslate",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        },
    ) { state ->
        when (state) {
            true -> 0f
            else -> -512f
        }
    }

    // ----------------------------------------------------------------

    val dot1ScaleOutScale by updateTransitionDot1.animateFloat(
        label = "dotsScaleOutScale",
        transitionSpec = {
            tween(
                durationMillis = 500,
            )
        },
    ) { state ->
        when (state) {
            true -> 1f
            else -> 0f
        }
    }

    val dot1SlideDownTranslate by updateTransitionDot1.animateFloat(
        label = "dotsSlideDownTranslate",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            )
        },
    ) { state ->
        when (state) {
            true -> 0f
            else -> -256f
        }
    }

    // ----------------------------------------------------------------

    val dot2ScaleOutScale by updateTransitionDot2.animateFloat(
        label = "dotsScaleOutScale",
        transitionSpec = {
            tween(
                durationMillis = 500,
            )
        },
    ) { state ->
        when (state) {
            true -> 1f
            else -> 0f
        }
    }

    val dot2SlideDownTranslate by updateTransitionDot2.animateFloat(
        label = "dotsSlideDownTranslate",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            )
        },
    ) { state ->
        when (state) {
            true -> 0f
            else -> -256f
        }
    }

    // ----------------------------------------------------------------

    val dot3ScaleOutScale by updateTransitionDot3.animateFloat(
        label = "dotsScaleOutScale",
        transitionSpec = {
            tween(
                durationMillis = 500,
            )
        },
    ) { state ->
        when (state) {
            true -> 1f
            else -> 0f
        }
    }

    val dot3SlideDownTranslate by updateTransitionDot3.animateFloat(
        label = "dotsSlideDownTranslate",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            )
        },
    ) { state ->
        when (state) {
            true -> 0f
            else -> -256f
        }
    }

    // ----------------------------------------------------------------

    val dot4ScaleOutScale by updateTransitionDot4.animateFloat(
        label = "dotsScaleOutScale",
        transitionSpec = {
            tween(
                durationMillis = 500,
            )
        },
    ) { state ->
        when (state) {
            true -> 1f
            else -> 0f
        }
    }

    val dot4SlideDownTranslate by updateTransitionDot4.animateFloat(
        label = "dotsSlideDownTranslate",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            )
        },
    ) { state ->
        when (state) {
            true -> 0f
            else -> -256f
        }
    }

    // ----------------------------------------------------------------

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_emudi_bg),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground.copy(alpha = .045f))
            )

            Box( // Yellow
                Modifier
                    .padding(end = 151.dp, bottom = 54.dp)
                    .size(11.5.dp)
                    .graphicsLayer {
                        scaleX = dot2ScaleOutScale
                        scaleY = dot2ScaleOutScale

                        translationY = dot2SlideDownTranslate
                    }
                    .background(Color(0xffFFED72), CircleShape)
            )

            Box( // Grey
                Modifier
                    .padding(end = 136.dp, bottom = 68.dp)
                    .size(5.dp)
                    .graphicsLayer {
                        scaleX = dot4ScaleOutScale
                        scaleY = dot4ScaleOutScale

                        translationY = dot4SlideDownTranslate
                    }
                    .background(Color(0xff93A9D7), CircleShape)
            )

            Box( // Red
                Modifier
                    .padding(end = 118.dp, bottom = 56.dp)
                    .size(12.5.dp)
                    .graphicsLayer {
                        scaleX = dot3ScaleOutScale
                        scaleY = dot3ScaleOutScale

                        translationY = dot3SlideDownTranslate
                    }
                    .background(Color(0xffFF3017), CircleShape)
            )

            Box( // Green
                Modifier
                    .padding(end = 132.dp, bottom = 25.dp)
                    .size(18.dp)
                    .graphicsLayer {
                        scaleX = dot1ScaleOutScale
                        scaleY = dot1ScaleOutScale

                        translationY = dot1SlideDownTranslate
                    }
                    .background(Color(0xff7CC966), CircleShape)
            )

            Image(
                modifier = Modifier
                    .padding(end = 142.dp)
                    .width(60.dp)
                    .graphicsLayer {
                        translationX = iconSlideFromLeftTranslate
                    },
                painter = painterResource(id = R.drawable.ic_emudi_part_1),
                contentDescription = "Cart",
                contentScale = ContentScale.Fit,
            )

            Image(
                modifier = Modifier
                    .padding()
                    .width(189.dp)
                    .graphicsLayer {
                        alpha = textFadeInAlpha
                    },
                painter = painterResource(id = R.drawable.ic_emudi_part_2),
                contentDescription = "Emudi",
                contentScale = ContentScale.Fit,
            )
        }
    }
}
