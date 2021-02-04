package ir.vbile.app.taravaz.services

import com.facebook.drawee.view.SimpleDraweeView
import ir.vbile.app.taravaz.view.TarAvazImageView

class FrescoImageLoadingServiceImpl : ImageLoadingService {
    override fun load(imageView: TarAvazImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(imageUrl)
        else
            throw IllegalStateException("ImageView must be instance of SimpleDraweeImageView")
    }
}