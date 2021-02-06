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
import ir.vbile.app.taravaz.services.ImageLoadingService
import kotlinx.android.synthetic.main.item_track_type1.view.ivCover
import kotlinx.android.synthetic.main.item_track_type1.view.tvArtistName
import kotlinx.android.synthetic.main.item_track_type1.view.tvTitle
import kotlinx.android.synthetic.main.item_track_type2.view.*
import org.koin.java.KoinJavaComponent.inject

class TrackAdapter(
    @LayoutRes val layoutId: Int = R.layout.item_track_type1,
    private val springAnimationTraitStatus: Boolean
) : TarAvazListAdapter<Track, BaseViewHolder<Track, Int>, Int>(diffUtil) {
    val imageLoadingService: ImageLoadingService by inject(ImageLoadingService::class.java)

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
                imageLoadingService.load(ivCover, item.cover)
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
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem.id == newItem.id
        }
    }

    lateinit var onMoreBtnClickListener: (Track) -> Unit
    fun setOnMoreBtnListener(callback: (Track) -> Unit) {
        onMoreBtnClickListener = callback
    }
}