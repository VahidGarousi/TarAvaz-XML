package ir.vbile.app.taravaz.feautre.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import ir.vbile.app.taravaz.data.Banner
import timber.log.Timber

class BannerSliderAdapter(
    fragment: Fragment,
    private val viewPager2: ViewPager2,
    private val banners: MutableList<Banner>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = banners.size
    override fun createFragment(position: Int): Fragment =
        BannerFragment.newInstance(banners[position])

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (position == banners.size - 2) {
            viewPager2.post(runnable)
        } else {
            Timber.i("")
        }
    }

    private val runnable = Runnable {
        banners.addAll(banners)
        notifyDataSetChanged()
    }
}