package com.example.app.util

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


/**
 * Workaround for previewing Image https://github.com/google/accompanist/issues/424
 */
@GlideModule
class ApplicationGlideModule : AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
