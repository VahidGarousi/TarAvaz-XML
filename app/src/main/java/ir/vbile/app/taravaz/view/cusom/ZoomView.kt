package ir.vbile.app.taravaz.view.cusom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import ir.vbile.app.taravaz.R
import kotlinx.android.synthetic.main.layout_zoom_view.view.*

class ZoomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(
    context,
    attrs,
    defStyleAttr
) {
    private var percent = 100
    private var maxPercent = 160
    private var minPercent = 80
    private var step = 20

    init {
        inflate(context, R.layout.layout_zoom_view, this)
    }

    private fun setPercent(percent: Int) {
        this.percent = percent
        tvPercent.text = "$percent%"
    }

    fun addOnMaximizeChangeListener(callback: (Int) -> Unit) {
        btnMaximize.setOnClickListener {
            percent += step
            if (percent <= maxPercent) {
                callback.invoke(percent)
                setPercent(percent)
            }
        }
    }

    fun addOnMinimizeChangeListener(callback: (Int) -> Unit) {
        btnMinimize.setOnClickListener {
            percent -= step
            if (percent >= minPercent) {
                callback.invoke(percent)
                setPercent(percent)
            }
        }
    }

}