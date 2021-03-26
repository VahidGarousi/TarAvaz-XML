package ir.vbile.app.taravaz.view.cusom.track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.BaseViewHolder
import ir.vbile.app.taravaz.common.TarAvazListAdapter
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.extentions.implementSpringAnimationTrait
import ir.vbile.app.taravaz.extentions.loadImage
import kotlinx.android.synthetic.main.item_track_type1.view.ivCover
import kotlinx.android.synthetic.main.item_track_type1.view.tvArtistName
import kotlinx.android.synthetic.main.item_track_type1.view.tvTitle
import kotlinx.android.synthetic.main.item_track_type2.view.*

class TrackAdapter(
    @LayoutRes val layoutId: Int = R.layout.item_track_type1,
    private val springAnimationTraitStatus: Boolean
) : TarAvazListAdapter<Track, BaseViewHolder<Track, Int>, Int>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Track, Int> =
        LayoutInflater.from(parent.context).run {
            TrackViewHolder(currentList, inflate(layoutId, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Track, Int>, position: Int) = holder.bind()

    inner class TrackViewHolder(list: List<Track>, itemView: View) :
        BaseViewHolder<Track, Int>(list, itemView, onItemEventListener) {
        override fun bind(position: Int, item: Track) {
            itemView.apply {
                tvTitle.text = item.title
                tvArtistName.text = item.songWriter
                loadImage(ivCover, item.cover)
                if (springAnimationTraitStatus)
                    implementSpringAnimationTrait()
                btnShowMore.setOnClickListener {
                    onMoreBtnClickListener.invoke(item)
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem.mediaId == newItem.mediaId

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem.mediaId == newItem.mediaId
        }
    }

    lateinit var onMoreBtnClickListener: (Track) -> Unit
    fun setOnMoreBtnListener(callback: (Track) -> Unit) {
        onMoreBtnClickListener = callback
    }
}