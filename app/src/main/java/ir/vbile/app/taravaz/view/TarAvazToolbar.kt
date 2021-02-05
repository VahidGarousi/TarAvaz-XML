package ir.vbile.app.taravaz.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.Toast
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.extentions.setVisibleOrGone
import kotlinx.android.synthetic.main.view_toolbar.view.*
import timber.log.Timber

class TarAvazToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
//    lateinit var toolbarTitle: TextView

    init {
        inflate(context, R.layout.view_toolbar, this)
//        toolbarTitle = tvTitle
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TarAvazToolbar)
            val title =
                a.getString(R.styleable.TarAvazToolbar_ta_title)
            val shouldShowBackBtn =
                a.getBoolean(R.styleable.TarAvazToolbar_ta_shouldShowBackBtn, false)
            if (title != null) {
                tvTitle.text = title
            }
            btnBack.setVisibleOrGone(shouldShowBackBtn)
            a.recycle()
        }
    }

    fun showBackBtn(shouldShow: Boolean, function: () -> Unit? = {}) {
        Timber.i(shouldShow.toString())
        btnBack.setVisibleOrGone(shouldShow)
        btnBack.setOnClickListener {
            function.invoke()
        }
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }
}