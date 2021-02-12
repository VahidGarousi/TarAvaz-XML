package ir.vbile.app.taravaz.services.android

import ir.vbile.app.taravaz.services.FrescoImageLoadingServiceImpl
import ir.vbile.app.taravaz.services.ImageLoadingService

object ImageLoadingProvider {
    private var imageLoadingService: ImageLoadingService? = null
    fun getService(): ImageLoadingService {
        return getFrescoImageLoadingService()
    }

    private fun getFrescoImageLoadingService(): ImageLoadingService {
        if (imageLoadingService == null) {
            imageLoadingService = FrescoImageLoadingServiceImpl()
        }
        return imageLoadingService!!
    }
}