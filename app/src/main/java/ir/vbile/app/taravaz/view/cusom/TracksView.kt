package ir.vbile.app.taravaz.view.cusom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.extentions.getEnum
import ir.vbile.app.taravaz.feautre.home.TrackAdapter
import kotlinx.android.synthetic.main.track_base_row.view.*

class BaseItemRow @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    fun submitList(it: List<Track>?) {
        trackAdapter.submitList(it)
    }

    private var trackAdapter: TrackAdapter

    init {
        // inflate the layout into "this" component
        inflate(context, R.layout.track_base_row, this)
        context.obtainStyledAttributes(attrs, R.styleable.BaseItemRow).apply {
            try {
                val title = getString(R.styleable.BaseItemRow_bir_rowTitle)
                val btnViewAllTitle = getString(R.styleable.BaseItemRow_bir_viewAllText)
                btnViewAll?.let {
                    it.text = btnViewAllTitle ?: context.getString(R.string.viewAll)
                }
                tvRowTitle?.let {
                    it.text = title ?: context.getString(R.string.newest_songs)
                }
                val layout =
                    getResourceId(R.styleable.BaseItemRow_bir_viewType, R.layout.item_track_type1)
                val orientation =
                    getEnum(R.styleable.BaseItemRow_bir_orientation, BirOrientation.Vertical)
                val layoutManager = when (getEnum(
                    R.styleable.BaseItemRow_bir_layoutManager,
                    BirLayoutManager.Linear
                )) {
                    BirLayoutManager.Linear -> LinearLayoutManager(
                        context,
                        orientation.value,
                        false
                    )
                    BirLayoutManager.Grid -> GridLayoutManager(context, 2)
                    BirLayoutManager.Staggered -> StaggeredGridLayoutManager(2, orientation.value)
                }
                rvItems.layoutManager = layoutManager
                val springAnimationTraitStatus =
                    getBoolean(R.styleable.BaseItemRow_springAnimationTraitStatus, false)
                trackAdapter = TrackAdapter(layout, springAnimationTraitStatus)
                rvItems.adapter = trackAdapter
            } finally {
                recycle()
            }
        }
    }
}

enum class BirLayoutManager(val value: Int) {
    Linear(0), Grid(1), Staggered(2)
}

enum class BirOrientation(val value: Int) {
    Horizontal(RecyclerView.HORIZONTAL), Vertical(RecyclerView.VERTICAL)
}