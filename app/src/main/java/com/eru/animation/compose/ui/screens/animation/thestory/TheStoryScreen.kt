package com.eru.animation.compose.ui.screens.animation.thestory

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eru.animation.compose.R
import com.eru.animation.compose.ui.theme.AppTheme
import com.eru.animation.compose.ui.theme.composeThemeColor
import kotlinx.coroutines.delay

@Composable
fun TheStoryScreen() {
    TheStoryScreenSkeleton()
}

@Preview
@Composable
fun TheStoryScreenSkeletonPreview() {
    AppTheme {
        TheStoryScreenSkeleton()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TheStoryScreenSkeletonPreviewDark() {
    AppTheme {
        TheStoryScreenSkeleton()
    }
}

@Composable
fun TheStoryScreenSkeleton() {
    var animState by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(100)

            animState += 1

            delay(3000)
        }
    }

    val animRotationZ1 by animateFloatAsState(
        targetValue = animState * 60f,
        animationSpec = tween(
            durationMillis = 800,
            easing = FastOutSlowInEasing,
        )
    )

    val animRotationZ2 by animateFloatAsState(
        targetValue = animState * 60f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 800,
            easing = FastOutSlowInEasing,
        )
    )

    val animRotationZ3 by animateFloatAsState(
        targetValue = animState * 60f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 900,
            easing = FastOutSlowInEasing,
        )
    )

    val animRotationZ4 by animateFloatAsState(
        targetValue = animState * 60f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 1000,
            easing = FastOutSlowInEasing,
        )
    )

    val animRotationZ5 by animateFloatAsState(
        targetValue = animState * 60f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 1100,
            easing = FastOutSlowInEasing,
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .requiredWidth((8 * 16 * 5).dp),
                contentAlignment = Alignment.Center,
            ) {

                Image(
                    modifier = Modifier
                        .size((8 * 16).dp)
                        .graphicsLayer {
                            rotationZ = animRotationZ1
                        },
                    painter = painterResource(id = R.drawable.ic_jetpack_compose_logo_outline),
                    contentDescription = "Jetpack Compose Outline Logo",
                    contentScale = ContentScale.Fit,
                )

                Image(
                    modifier = Modifier
                        .graphicsLayer {
                            rotationZ = animRotationZ2
                        }
                        .requiredSize((8 * 16 * 2).dp)
                        .padding(8.dp)
                        .alpha(1f),
                    painter = painterResource(id = R.drawable.ic_hexagon_outline_4),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )

                Image(
                    modifier = Modifier
                        .graphicsLayer {
                            rotationZ = animRotationZ3
                        }
                        .requiredSize((8 * 16 * 3).dp)
                        .padding(8.dp)
                        .alpha(.7f),
                    painter = painterResource(id = R.drawable.ic_hexagon_outline_3),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )

                Image(
                    modifier = Modifier
                        .graphicsLayer {
                            rotationZ = animRotationZ4
                        }
                        .requiredSize((8 * 16 * 4).dp)
                        .padding(8.dp)
                        .alpha(.4f),
                    painter = painterResource(id = R.drawable.ic_hexagon_outline_2),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )

                Image(
                    modifier = Modifier
                        .requiredSize((8 * 16 * 5).dp)
                        .padding(8.dp)
                        .alpha(.1f)
                        .graphicsLayer {
                            rotationZ = animRotationZ5
                        },
                    painter = painterResource(id = R.drawable.ic_hexagon_outline_1),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 32.dp, top = 32.dp),
                text = buildAnnotatedString {
                    append("Why Not ")

                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colors.onBackground,
                        )
                    ) {
                        append("Compose!")
                    }
                },
                color = MaterialTheme.colors.composeThemeColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}
