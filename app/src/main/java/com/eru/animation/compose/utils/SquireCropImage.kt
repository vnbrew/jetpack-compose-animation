package com.eru.animation.compose.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import com.eru.animation.compose.BuildConfig
import com.yalantis.ucrop.UCrop
import timber.log.Timber

/**
 * This is using UCrop to crop/resize an image.
 *
 * Usage:
 * ```
 * val uCropLauncher = rememberLauncherForActivityResult(SquireCropImage()) { uri ->
 *     uri?.let {
 *         // the "uri" holds the final image
 *     }
 * }
 *
 * uCropLauncher.launch(
 *     Pair(
 *         first = uri, // <-- Uri from Camera or Gallery
 *         second = Uri.fromFile(File(context.cacheDir, "temp_image_file"))
 *     )
 * )
 * ```
 */
class SquireCropImage : ActivityResultContract<Pair<Uri, Uri>, Uri?>() {
    override fun createIntent(context: Context, input: Pair<Uri, Uri>): Intent =
        UCrop.of(input.first, input.second)
            .withAspectRatio(1f, 1f)
            .getIntent(context)

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode != Activity.RESULT_OK || intent == null) {
            return null
        } else if (BuildConfig.DEBUG && resultCode == UCrop.RESULT_ERROR) {
            UCrop.getError(intent)?.let { cropError ->
                Timber.e("cropError: $cropError")
            }
        }
        return UCrop.getOutput(intent)
    }
}
