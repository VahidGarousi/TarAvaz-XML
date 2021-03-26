package ir.vbile.app.taravaz.feautre.track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.data.Couplet
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_lyric.view.*

class CoupletAdapter : ListAdapter<Couplet, CoupletAdapter.LyricViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricViewHolder {
        return LyricViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_lyric, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LyricViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class LyricViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView.rootView) {
        fun bind(couplet: Couplet) {
            containerView.apply {
                tvHemistich1.text = couplet.hemistich.first.text
                tvHemistich1.text = couplet.hemistich.second.text
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Couplet>() {
            override fun areItemsTheSame(oldItem: Couplet, newItem: Couplet): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Couplet, newItem: Couplet): Boolean =
                oldItem.hashCode() == newItem.hashCode()
        }
    }
}