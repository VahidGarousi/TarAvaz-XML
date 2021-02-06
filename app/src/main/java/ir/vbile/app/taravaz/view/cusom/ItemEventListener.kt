package ir.vbile.app.taravaz.view.cusom

interface ItemEventListener<T, E :  Number> {
    fun onClick(item: T, position: E)
    fun onLongClick(item: T, position: E)
}