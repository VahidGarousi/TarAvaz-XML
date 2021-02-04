package ir.vbile.app.taravaz.feautre.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.vbile.app.taravaz.data.Banner

class BannerSliderAdapter(fragment: Fragment, private val banners: List<Banner>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = banners.size
    override fun createFragment(position: Int): Fragment =
        BannerFragment.newInstance(banners[position])
}