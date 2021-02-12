package ir.vbile.app.taravaz.di

import ir.vbile.app.taravaz.services.FrescoImageLoadingServiceImpl
import ir.vbile.app.taravaz.services.ImageLoadingService

object ImageLoadingProvider {

    private var imageLoadingService: ImageLoadingService? = null
    fun getService(): ImageLoadingService {
        return getPicassoImageLoadingService()
    }

    private fun getPicassoImageLoadingService(): ImageLoadingService {
        if (imageLoadingService == null) {
            imageLoadingService = FrescoImageLoadingServiceImpl()
        }
        return imageLoadingService!!
    }
}