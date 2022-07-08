package com.eru.animation.compose.utils

import com.eru.animation.compose.BuildConfig

object Constants {
    /**
     * Server endpoint without end slash.
     */
    const val SERVER_ENDPOINT = "https://gorest.co.in/public-api"

    /**
     * For MyNotificationOpenedHandler
     */
    const val INTENT_EXTRA_TARGET_KEY = "target"
    const val INTENT_EXTRA_TARGET_VAL_NOTIFICATIONS = "notifications"

    /**
     * For Broadcast
     */
    const val BROADCAST_ACTION_NOTIFICATIONS = BuildConfig.APPLICATION_ID + ".notifications"
}
