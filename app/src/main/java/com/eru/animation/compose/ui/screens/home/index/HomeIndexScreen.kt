package com.eru.animation.compose.ui.screens.home.index

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eru.animation.compose.R
import com.eru.animation.compose.ui.screens.Screen
import com.eru.animation.compose.ui.theme.AppTheme
import com.eru.animation.compose.ui.theme.TailwindCSSColor
import com.eru.animation.compose.utils.extensions.shadow
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsHeight

private val menuItems = listOf(
    MenuItem(
        name = "Animations",
        icon = R.drawable.ic_round_animation_24,
        color = TailwindCSSColor.Yellow500,
        route = Screen.Animations
    ),
    MenuItem(
        name = "Compositions",
        icon = R.drawable.ic_round_widgets_24,
        color = TailwindCSSColor.Red500,
        route = Screen.Compositions
    ),
    MenuItem(
        name = "UIs",
        icon = R.drawable.ic_round_grid_view_24,
        color = TailwindCSSColor.Blue500,
        route = Screen.UIs
    ),
    MenuItem(
        name = "Tutorials",
        icon = R.drawable.ic_round_sticky_note_2_24,
        color = TailwindCSSColor.Purple500,
        route = Screen.Tutorials
    ),
)

@ExperimentalFoundationApi
@Composable
fun HomeIndexScreen(
    navigate: (Screen) -> Unit = {},
    turnOnDarkMode: (Boolean) -> Unit = {},
) {
    val isDark = !MaterialTheme.colors.isLight
    val (darkModeState, onDarkModeStateChange) = remember { mutableStateOf(isDark) }

    Scaffold(
        modifier = Modifier.navigationBarsWithImePadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(24.dp, 8.dp, 24.dp, 24.dp)
                ) {
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(modifier = Modifier.statusBarsHeight())
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        top = 32.dp,
                                        end = 16.dp,
                                        bottom = 32.dp
                                    )
                                    .fillMaxWidth(),
                                text = stringResource(id = R.string.app_name),
                                style = MaterialTheme.typography.h1,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    item {
                        ModuleButton(
                            name = if (isDark)
                                stringResource(id = R.string.dark_mode)
                            else
                                stringResource(id = R.string.light_mode),
                            icon = if (isDark)
                                R.drawable.ic_moon_stars
                            else
                                R.drawable.ic_brightness_high,
                            color = TailwindCSSColor.Green500,
                            onClick = {
                                onDarkModeStateChange(!darkModeState)
                                turnOnDarkMode(!darkModeState)
                            }
                        )
                    }

                    items(menuItems) { menu ->
                        ModuleButton(
                            name = menu.name,
                            icon = menu.icon,
                            color = menu.color,
                            onClick = {
                                navigate(menu.route)
                            }
                        )
                    }
                }

                Box(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(
                            Brush.verticalGradient(
                                0f to Color.Transparent,
                                1f to MaterialTheme.colors.background
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun ModuleButton(
    name: String,
    @DrawableRes icon: Int,
    color: Color,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .shadow(
                spread = 8.dp,
                alpha = .25f,
                color = color,
                radius = 8.dp,
            ),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = color
        ),
        onClick = onClick,
        contentPadding = PaddingValues(8.dp),
        elevation = null,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = icon),
                contentDescription = name,
                tint = LocalContentColor.current,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = name,
                color = LocalContentColor.current,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
fun HomeIndexScreenPreview() {
    AppTheme {
        HomeIndexScreen()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeIndexScreenPreviewDark() {
    AppTheme {
        HomeIndexScreen()
    }
}

data class MenuItem(
    val name: String,
    @DrawableRes val icon: Int,
    val color: Color,
    val route: Screen,
)
