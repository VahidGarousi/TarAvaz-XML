package ir.vbile.app.taravaz.feautre.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.extentions.convertDpToPixel
import kotlinx.android.synthetic.main.base_item_row.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.abs


class HomeFragment : TarAvazFragment(R.layout.fragment_home) {
    val vm: HomeVM by viewModel()
    lateinit var bannerSliderAdapter: BannerSliderAdapter
    val sliderHandler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        setUpSlider()
        setUpTrackAdapter()
        rowNewest.btnViewAll.setOnClickListener {
            longToast("مشاهده همه")
        }
        rowPopular.btnViewAll.setOnClickListener {
            longToast("همه مشاهده")
        }
    }

    private fun setUpTrackAdapter() {

    }

    private fun setUpSlider() {
        bannerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 0.3f - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        bannerSlider.setPageTransformer(compositePageTransformer)
        bannerSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
    }

    private val sliderRunnable = Runnable {
        bannerSlider.currentItem = bannerSlider.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    private fun subscribeToObservers() {
        vm.banners.observe(viewLifecycleOwner) {
            bannerSliderAdapter = BannerSliderAdapter(this, bannerSlider, it.toMutableList())
            bannerSlider.apply {
                offscreenPageLimit = 3
                adapter = bannerSliderAdapter
                /*val bannerSliderHeight =
                    (((bannerSlider.width - convertDpToPixel(
                        32F,
                        requireContext()
                    )) * 140) / 253)
                val lp = layoutParams
                lp.height = bannerSliderHeight
                layoutParams = lp*/
            }
            bannerSlider.currentItem = 1
        }
        vm.tracks.observe(viewLifecycleOwner) {
            rowNewest.trackAdapter.submitList(it)
            rowPopular.trackAdapter.submitList(it)
        }
    }
}
