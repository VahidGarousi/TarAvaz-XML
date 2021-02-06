package ir.vbile.app.taravaz.view.cusom.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.BaseViewHolder
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.extentions.implementSpringAnimationTrait
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.item_album_type1.view.*
import org.koin.java.KoinJavaComponent.inject

class AlbumAdapter constructor(
    @LayoutRes
    val layoutId: Int = R.layout.item_album_type1,
    private val springAnimationTraitStatus: Boolean
) : ListAdapter<Album, BaseViewHolder<Album>>(diffUtil) {
    val imageLoadingService: ImageLoadingService by inject(ImageLoadingService::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Album> =
        LayoutInflater.from(parent.context).run {
            AlbumViewHolder(inflate(layoutId, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Album>, position: Int) =
        holder.bind(holder.itemView, getItem(position))

    inner class AlbumViewHolder(itemView: View) : BaseViewHolder<Album>(itemView) {
        override fun bind(itemView: View, item: Album) {
            itemView.apply {
                tvAlbumName.text = item.name
                tvArtistName.text = item.name
                imageLoadingService.load(ivCover, item.cover)
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