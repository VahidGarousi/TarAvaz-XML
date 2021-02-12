package ir.vbile.app.taravaz.view.cusom.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.BaseViewHolder
import ir.vbile.app.taravaz.common.TarAvazListAdapter
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.extentions.implementSpringAnimationTrait
import ir.vbile.app.taravaz.extentions.loadImage
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.item_album_type1.view.*
import javax.inject.Inject

class AlbumAdapter constructor(
    @LayoutRes
    val layoutId: Int = R.layout.item_album_type1,
    private val springAnimationTraitStatus: Boolean
) : TarAvazListAdapter<Album, BaseViewHolder<Album, Int>, Int>(diffUtil) {
//    @Inject
//    lateinit var  imageLoadingService: ImageLoadingService

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Album, Int> =
        LayoutInflater.from(parent.context).run {
            AlbumViewHolder(currentList, inflate(layoutId, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Album, Int>, position: Int) = holder.bind()

    inner class AlbumViewHolder(currentList: List<Album>, itemView: View) :
        BaseViewHolder<Album, Int>(currentList, itemView, onItemEventListener) {
        override fun bind(position: Int, item: Album) {
            itemView.apply {
                tvAlbumName.text = item.name
                tvArtistName.text = item.name
                loadImage(ivCover, item.cover)
                if (springAnimationTraitStatus)
                    implementSpringAnimationTrait()
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem.id == newItem.id
        }
    }

}