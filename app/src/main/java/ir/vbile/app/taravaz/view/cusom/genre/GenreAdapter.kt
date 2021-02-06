package ir.vbile.app.taravaz.view.cusom.genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.BaseViewHolder
import ir.vbile.app.taravaz.common.TarAvazListAdapter
import ir.vbile.app.taravaz.data.Genre
import ir.vbile.app.taravaz.extentions.implementSpringAnimationTrait
import kotlinx.android.synthetic.main.item_genre_type1.view.*

class GenreAdapter(
    @LayoutRes val layoutId: Int = R.layout.item_genre_type1,
    private val springAnimationTraitStatus: Boolean
) :
    TarAvazListAdapter<Genre, BaseViewHolder<Genre, Int>, Int>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Genre, Int> =
        LayoutInflater.from(parent.context).run {
            GenreViewHolder(inflate(layoutId, parent, false))
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Genre, Int>, position: Int) = holder.bind()
    inner class GenreViewHolder(itemView: View) :
        BaseViewHolder<Genre, Int>(currentList, itemView, onItemEventListener) {
        override fun bind(position: Int, item: Genre) {
            itemView.run {
                if (springAnimationTraitStatus)
                    implementSpringAnimationTrait()
                tvTrackCount.text = item.tracksCount.toString()
                tvName.text = item.name
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean =
                oldItem.id == newItem.id
        }
    }
}