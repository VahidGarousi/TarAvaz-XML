package ir.vbile.app.taravaz.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ir.vbile.app.taravaz.view.cusom.ItemEventListener

abstract class BaseViewHolder<T, E : Number>(
    private val items: List<T>,
    itemView: View,
    private var onItemEventListener: ItemEventListener<T, Int>?
) :
    RecyclerView.ViewHolder(itemView) {
    fun bind() {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            bind(
                adapterPosition,
                items[adapterPosition]
            )
            itemView.setOnClickListener {
                onItemEventListener?.onClick(items[adapterPosition], adapterPosition)
            }
            itemView.setOnLongClickListener {
                onItemEventListener?.onLongClick(items[adapterPosition], adapterPosition)
                true
            }
        }
    }

    protected abstract fun bind(position: Int, item: T)

}