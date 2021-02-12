package ir.vbile.app.taravaz.services

import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import ir.vbile.app.taravaz.view.TarAvazImageView
import javax.inject.Inject
import javax.inject.Singleton

class FrescoImageLoadingServiceImpl @Inject constructor() : ImageLoadingService {
    override fun load(imageView: ImageView, imageUrl: String?) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(imageUrl)
        else
            throw IllegalStateException("ImageView must be instance of SimpleDraweeImageView")
    }
}