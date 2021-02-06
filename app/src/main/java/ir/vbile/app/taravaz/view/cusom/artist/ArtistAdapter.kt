package ir.vbile.app.taravaz.view.cusom.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.BaseViewHolder
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.extentions.implementSpringAnimationTrait
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.item_artist_type1.view.*
import org.koin.java.KoinJavaComponent.inject

class ArtistAdapter(
    @LayoutRes val layoutId: Int = R.layout.item_artist_type1,
    private val springAnimationTraitStatus: Boolean
) : ListAdapter<Artist, BaseViewHolder<Artist>>(diffUtil) {
    val imageLoadingService: ImageLoadingService by inject(ImageLoadingService::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Artist> =
        LayoutInflater.from(parent.context).run {
            ArtistViewHolder(inflate(layoutId, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Artist>, position: Int) =
        holder.bind(holder.itemView, getItem(position))

    inner class ArtistViewHolder(itemView: View) : BaseViewHolder<Artist>(itemView) {
        override fun bind(itemView: View, item: Artist) {
            itemView.apply {
                tvName.text = item.name
                imageLoadingService.load(ivCover, item.image)
                if (springAnimationTraitStatus)
                    implementSpringAnimationTrait()
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem.id == newItem.id
        }
    }

}