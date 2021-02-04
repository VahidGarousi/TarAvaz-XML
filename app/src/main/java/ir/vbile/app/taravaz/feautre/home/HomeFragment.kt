package ir.vbile.app.taravaz.feautre.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.extentions.convertDpToPixel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.abs


class HomeFragment : TarAvazFragment(R.layout.fragment_home) {
    val vm: HomeVM by viewModel()
    lateinit var bannerSliderAdapter: BannerSliderAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        bannerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        bannerSlider.setPageTransformer(compositePageTransformer)
    }

    private fun subscribeToObservers() {
        vm.banners.observe(viewLifecycleOwner) {
            bannerSliderAdapter = BannerSliderAdapter(this,it)
            bannerSlider.apply {
                offscreenPageLimit = 3
                adapter = bannerSliderAdapter
                val bannerSliderHeight =
                    (((bannerSlider.width - convertDpToPixel(
                        32F,
                        requireContext()
                    )) * 140) / 253)
                val lp = layoutParams
                lp.height = bannerSliderHeight
                layoutParams = lp
            }
            sliderIndicator.setViewPager2(bannerSlider)
        }
    }
}
