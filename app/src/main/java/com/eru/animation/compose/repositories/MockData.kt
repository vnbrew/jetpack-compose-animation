package com.eru.animation.compose.repositories

import com.eru.animation.compose.models.ListItem
import com.eru.animation.compose.models.User
import com.eru.animation.compose.models.github.GithubRepo
import com.eru.animation.compose.models.github.Owner

object MockData {
    val dummyUser = User(
        id = 1,
        name = "Hello babies!"
    )

    val dummyListItem = listOf(
        ListItem(id = 1, name = "Cupcake"),
        ListItem(id = 2, name = "Donut"),
        ListItem(id = 3, name = "Eclair"),
        ListItem(id = 4, name = "Froyo"),
        ListItem(id = 5, name = "Gingerbread"),
        ListItem(id = 6, name = "Honeycomb"),
        ListItem(id = 7, name = "Ice cream sandwich"),
        ListItem(id = 8, name = "Jelly bean"),
        ListItem(id = 9, name = "KitKat"),
        ListItem(id = 10, name = "Lollipop"),
        ListItem(id = 11, name = "Marshmallow"),
        ListItem(id = 12, name = "Nougat"),
        ListItem(id = 13, name = "Oreo"),
        ListItem(id = 14, name = "Pie"),
    )

    val dummyGithubRepo = GithubRepo(
        id = 1,
        name = "Compose",
        fullName = "Jetpacl Compose",
        owner = Owner(
            login = "ImaginativeShohag",
            avatarUrl = ""
        ),
        description = "A collection of animations, compositions, UIs using Jetpack Compose. You can say Jetpack Compose cookbook or play-ground if you want!",
        stargazersCount = 100,
        watchersCount = 200,
        forksCount = 300,
    )
}
