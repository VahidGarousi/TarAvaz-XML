package ir.vbile.app.taravaz.view.cusom.genre

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.data.Genre
import ir.vbile.app.taravaz.extentions.getEnum
import ir.vbile.app.taravaz.view.cusom.track.BirLayoutManager
import ir.vbile.app.taravaz.view.cusom.track.BirOrientation
import kotlinx.android.synthetic.main.base_artist_row.view.*

class GenresView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var currentList: List<Genre>
    protected lateinit var genreAdapter: GenreAdapter
    fun submitList(it: List<Genre>?) {
        it?.let {
            currentList = it
        }
        genreAdapter.submitList(it)
    }

    init {
        inflate(context, R.layout.base_genre_row, this)
        context.obtainStyledAttributes(attrs, R.styleable.GenresView).apply {
            try {
                val title = getString(R.styleable.GenresView_genre_rowTitle)
                val btnViewAllTitle = getString(R.styleable.GenresView_genre_viewAllText)
                btnViewAll?.let {
                    it.text = btnViewAllTitle ?: context.getString(R.string.viewAll)
                }
                tvRowTitle?.let {
                    it.text = title ?: context.getString(R.string.latest_albums)
                }
                val layout =
                    getResourceId(R.styleable.GenresView_genre_viewType, R.layout.item_track_type1)
                val orientation =
                    getEnum(R.styleable.GenresView_genre_orientation, BirOrientation.Vertical)
                val spanCount = getInteger(R.styleable.GenresView_genre_spanCount,2)
                val layoutManager = when (getEnum(
                    R.styleable.GenresView_genre_layoutManager,
                    BirLayoutManager.Linear
                )) {
                    BirLayoutManager.Linear -> LinearLayoutManager(
                        context,
                        orientation.value,
                        false
                    )
                    BirLayoutManager.Grid -> GridLayoutManager(context, spanCount)
                    BirLayoutManager.Staggered -> StaggeredGridLayoutManager(spanCount, orientation.value)
                }
                rvItems.layoutManager = layoutManager
                val springAnimationTraitStatus = getBoolean(R.styleable.GenresView_genre_springAnimationTraitStatus, false)
                genreAdapter = GenreAdapter(layout, springAnimationTraitStatus)
                rvItems.adapter = genreAdapter
            } finally {
                recycle()
            }
        }
    }
}