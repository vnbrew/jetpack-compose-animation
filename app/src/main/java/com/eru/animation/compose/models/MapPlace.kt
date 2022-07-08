package com.eru.animation.compose.models

import androidx.annotation.Keep
import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MapPlace(
    val name: String,
    val location: LatLng,
    val description: String,
)
