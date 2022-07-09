package com.eru.animation.compose.ui.screens.animation.animatedvisibility

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.DismissValue.DismissedToEnd
import androidx.compose.material.DismissValue.DismissedToStart
import androidx.compose.material.Divider
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eru.animation.compose.models.ListItem
import com.eru.animation.compose.repositories.MockData
import com.eru.animation.compose.ui.compositions.AppComponent
import com.eru.animation.compose.ui.theme.AppTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val dummyItems = MockData.dummyListItem.toList()

@Composable
fun AnimatedVisibilityScreen() {
    val items = remember { mutableStateOf(dummyItems) }

    AnimatedVisibilityScreenSkeleton(
        items = items.value,
        onDelete = {
            items.value = items.value.toMutableList().apply {
                remove(it)
            }
        }
    )
}

@Preview
@Composable
fun AnimatedVisibilityScreenSkeletonPreview() {
    AppTheme {
        val items = remember { mutableStateOf(dummyItems) }

        AnimatedVisibilityScreenSkeleton(
            items = items.value,
            onDelete = {
                items.value = items.value.toMutableList().apply {
                    remove(it)
                }
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AnimatedVisibilityScreenSkeletonPreviewDark() {
    AppTheme {
        val items = remember { mutableStateOf(dummyItems) }

        AnimatedVisibilityScreenSkeleton(
            items = items.value,
            onDelete = {
                items.value = items.value.toMutableList().apply {
                    remove(it)
                }
            }
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AnimatedVisibilityScreenSkeleton(
    items: List<ListItem>,
    onDelete: (ListItem) -> Unit,
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        Modifier
            .navigationBarsWithImePadding()
            .statusBarsPadding()
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            AppComponent.Header("AnimatedVisibility")

            Divider()

            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
            ) {
                items(
                    items = items,
                    key = { listItem: ListItem -> listItem.id }
                ) { item ->
                    var deleted by remember { mutableStateOf(false) }
                    var unread by remember { mutableStateOf(true) }
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissedToEnd) unread = !unread

                            if (it == DismissedToStart) {
                                deleted = true
                                return@rememberDismissState true
                            }
                            return@rememberDismissState false
                        }
                    )

                    if (deleted) {
                        scope.launch {
                            delay(500)

                            onDelete(item)
                        }
                    }

                    AnimatedVisibility(
                        visible = !deleted,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {

                        SwipeToDismiss(
                            state = dismissState,
                            modifier = Modifier.padding(vertical = 4.dp),
                            directions = setOf(
                                StartToEnd,
                                EndToStart
                            ),
                            dismissThresholds = { direction ->
                                FractionalThreshold(if (direction == StartToEnd) 0.25f else 0.5f)
                            },
                            background = {
                                val direction =
                                    dismissState.dismissDirection ?: return@SwipeToDismiss
                                val color by animateColorAsState(
                                    when (dismissState.targetValue) {
                                        Default -> Color.LightGray
                                        DismissedToEnd -> Color.Green
                                        DismissedToStart -> Color.Red
                                    }
                                )
                                val alignment = when (direction) {
                                    StartToEnd -> Alignment.CenterStart
                                    EndToStart -> Alignment.CenterEnd
                                }
                                val icon = when (direction) {
                                    StartToEnd -> Icons.Default.Done
                                    EndToStart -> Icons.Default.Delete
                                }
                                val scale by animateFloatAsState(
                                    if (dismissState.targetValue == Default) 0.75f else 1f
                                )

                                Box(
                                    Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(horizontal = 20.dp),
                                    contentAlignment = alignment
                                ) {
                                    Icon(
                                        icon,
                                        contentDescription = "Localized description",
                                        modifier = Modifier.scale(scale)
                                    )
                                }
                            },
                            dismissContent = {
                                Card(
                                    elevation = animateDpAsState(
                                        if (dismissState.dismissDirection != null) 4.dp else 0.dp
                                    ).value
                                ) {
                                    ListItem(
                                        text = {
                                            Text(
                                                item.name,
                                                fontWeight = if (unread) FontWeight.Bold else null,
                                                textDecoration = if (unread) TextDecoration.None else TextDecoration.LineThrough,
                                            )
                                        },
                                        secondaryText = { Text("Swipe me left or right!") }
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
