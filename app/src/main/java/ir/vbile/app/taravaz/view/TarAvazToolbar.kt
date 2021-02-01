package ir.vbile.app.taravaz.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.extentions.setVisibleOrGone
import kotlinx.android.synthetic.main.view_toolbar.view.*

class TarAvazToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.view_toolbar, this)
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TarAvazToolbar)
            val title = a.getString(R.styleable.TarAvazToolbar_ta_title)
            val shouldShowBackBtn =
                a.getBoolean(R.styleable.TarAvazToolbar_ta_shouldShowBackBtn, false)
            if (title != null) {
                tvTitle.text = title
            }
            btnBack.setVisibleOrGone(shouldShowBackBtn)
            a.recycle()
        }
    }

    fun showBackBtn(shouldShow: Boolean) {
        if (shouldShow){
            btnBack.setVisibleOrGone(shouldShow)
        }
    }
}