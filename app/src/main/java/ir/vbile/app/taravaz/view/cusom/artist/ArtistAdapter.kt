package ir.vbile.app.taravaz.view.cusom.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.BaseViewHolder
import ir.vbile.app.taravaz.common.TarAvazListAdapter
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.extentions.implementSpringAnimationTrait
import ir.vbile.app.taravaz.extentions.loadImage
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.item_artist_type1.view.*
import javax.inject.Inject

class ArtistAdapter(
    @LayoutRes val layoutId: Int = R.layout.item_artist_type1,
    private val springAnimationTraitStatus: Boolean
) : TarAvazListAdapter<Artist, BaseViewHolder<Artist, Int>, Int>(diffUtil) {
//    @Inject
//    lateinit var imageLoadingService: ImageLoadingService

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Artist, Int> =
        LayoutInflater.from(parent.context).run {
            ArtistViewHolder(currentList, inflate(layoutId, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Artist, Int>, position: Int) =
        holder.bind()

    inner class ArtistViewHolder(list: List<Artist>, itemView: View) :
        BaseViewHolder<Artist, Int>(list, itemView, onItemEventListener) {
        override fun bind(position: Int, item: Artist) {
            itemView.apply {
                tvName.text = item.name
                loadImage(ivCover, item.image)
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