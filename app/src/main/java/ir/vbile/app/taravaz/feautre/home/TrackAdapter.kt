package ir.vbile.app.taravaz.feautre.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.BaseViewHolder
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.extentions.implementSpringAnimationTrait
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.item_song_type1.view.*
import org.koin.java.KoinJavaComponent.inject

class TrackAdapter : ListAdapter<Track, BaseViewHolder<Track>>(diffUtil) {
    val imageLoadingService: ImageLoadingService by inject(ImageLoadingService::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Track> =
        LayoutInflater.from(parent.context).run {
            TrackViewHolder(inflate(R.layout.item_song_type1, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Track>, position: Int) =
        holder.bind(holder.itemView, getItem(position))

    inner class TrackViewHolder(itemView: View) : BaseViewHolder<Track>(itemView) {
        override fun bind(itemView: View, item: Track) = itemView.run {
            tvTitle.text = item.title
            tvArtistName.text = item.songWriter
            imageLoadingService.load(ivCover, item.cover)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem.hashCode() == newItem.hashCode()
        }
    }

}