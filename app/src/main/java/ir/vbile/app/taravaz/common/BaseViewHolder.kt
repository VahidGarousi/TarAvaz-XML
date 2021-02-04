package ir.vbile.app.taravaz.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(itemView: View, item: T)
}