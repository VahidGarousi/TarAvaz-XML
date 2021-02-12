package ir.vbile.app.taravaz.feautre.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.EXTRA_KEY_DATA
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.services.ImageLoadingService
import ir.vbile.app.taravaz.view.TarAvazImageView
import javax.inject.Inject
@AndroidEntryPoint
class BannerFragment : Fragment() {
    @Inject
    lateinit var imageLoadingService: ImageLoadingService
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageView = inflater.inflate(
            R.layout.fragment_banner,
            container,
            false
        ) as TarAvazImageView
        val banner = requireArguments()
            .getParcelable<Banner>(EXTRA_KEY_DATA)
            ?: throw IllegalStateException("Banner cannot be null")
        imageLoadingService.load(imageView, banner.image)
        return imageView
    }

    companion object {
        fun newInstance(banner: Banner): BannerFragment {
            return BannerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_KEY_DATA, banner)
                }
            }
        }
    }
}