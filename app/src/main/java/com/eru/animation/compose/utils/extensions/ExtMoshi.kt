package com.eru.animation.compose.utils.extensions

import com.squareup.moshi.Moshi
import com.eru.animation.compose.network.ApiClient
import timber.log.Timber
import java.net.URLEncoder
import java.util.Date

object MoshiUtil {
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, ApiClient.DateJsonAdapter())
            .build()
    }
}

inline fun <reified T> String?.getObjFromJson(): T? {
    if (this == null) return null

    Timber.e("getObjFromJson: $this")

    val jsonAdapter = MoshiUtil.getMoshi().adapter(T::class.java).lenient()

    return jsonAdapter.fromJson(this)
}

inline fun <reified T> T?.getJsonFromObj(urlEncode: Boolean = true): String? {
    if (this == null) return null

    Timber.e("getJsonFromObj: $this")

    val jsonAdapter = MoshiUtil.getMoshi().adapter(T::class.java).lenient()

    return jsonAdapter.toJson(this).let { json ->
        if (urlEncode) json.urlEncode() else json
    }
}

fun String.urlEncode(): String {
    return URLEncoder.encode(this, "utf-8")
}
