package com.eru.animation.compose.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.eru.animation.compose.ui.theme.AppTheme
import com.eru.animation.compose.utils.SharedPref
import com.eru.animation.compose.utils.extensions.toast
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPref: SharedPref
    private var pressBackExitJob: Job? = null
    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        var isDarkMode by mutableStateOf(sharedPref.getDarkMode())
        setContent {
            AppTheme(
                darkTheme = isDarkMode
            ) {
                ProvideWindowInsets {
                    MainScreen(
                        isDarkMode = isDarkMode,
                        turnOnDarkMode = { turnOn ->
                            isDarkMode = turnOn
                            sharedPref.setDarkMode(turnOn)
                        }
                    )
                }
            }
        }

        // Press double back to exit
        onBackPressedDispatcher.addCallback(this) {
            pressBackExitJob?.cancel()
            if (backPressedOnce) {
                finish()
                return@addCallback
            }
            toast("Please press back again to exit.")
            backPressedOnce = true
            pressBackExitJob = lifecycleScope.launch {
                delay(500)
                backPressedOnce = false
            }
        }
//        @Composable
//        fun MyAnimation(targetSize: Int) {
//
//            val IntToVector: TwoWayConverter<Int, AnimationVector1D> = TwoWayConverter(
//                convertToVector = { value -> AnimationVector1D(value.toFloat()) },
//                convertFromVector = { vector -> vector.value.toInt() }
//            )
//            val customAnim: Int by animateValueAsState<Int, AnimationVector1D>(
//                targetValue = targetSize,
//                typeConverter = IntToVector
//            )
//        }
//
//        data class Position(val x: Float, val y: Float)
//        @Composable
//        fun animatePositionAsState(
//            targetValue: Position,
//            animationSpec: AnimationSpec<Position> = spring<Position>(),
//            visibilityThreshold: Position = Position(0.0f, 0.0f),
//            finishedListener: ((Position) -> Unit)? = null
//        ): State<Position> {
//            return animateValueAsState(
//                targetValue = targetValue,
//                typeConverter = TwoWayConverter(
//                    convertToVector = { pos -> AnimationVector2D(pos.x, pos.y) },
//                    convertFromVector = { vector -> Position(vector.v1, vector.v2) }
//                ),
//                animationSpec = animationSpec,
//                visibilityThreshold = visibilityThreshold,
//                finishedListener = finishedListener
//            )
//        }
    }
}
