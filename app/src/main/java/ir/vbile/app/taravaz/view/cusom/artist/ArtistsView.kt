package ir.vbile.app.taravaz.view.cusom.artist

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.extentions.getEnum
import ir.vbile.app.taravaz.view.cusom.track.BirLayoutManager
import ir.vbile.app.taravaz.view.cusom.track.BirOrientation
import kotlinx.android.synthetic.main.base_artist_row.view.*

class ArtistsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val artistAdapter: ArtistAdapter
    fun submitList(it: List<Artist>?) {
        artistAdapter.submitList(it)
    }

    init {
        inflate(context, R.layout.base_artist_row, this)
        context.obtainStyledAttributes(attrs, R.styleable.ArtistsView).apply {
            try {
                val title = getString(R.styleable.ArtistsView_av_rowTitle)
                val btnViewAllTitle = getString(R.styleable.ArtistsView_av_viewAllText)
                btnViewAll?.let {
                    it.text = btnViewAllTitle ?: context.getString(R.string.viewAll)
                }
                tvRowTitle?.let {
                    it.text = title ?: context.getString(R.string.newest_songs)
                }
                val layout =
                    getResourceId(R.styleable.ArtistsView_av_viewType, R.layout.item_track_type1)
                val orientation =
                    getEnum(R.styleable.ArtistsView_av_orientation, BirOrientation.Vertical)
                val spanCount = getInteger(R.styleable.ArtistsView_av_spanCount,2)
                val layoutManager = when (getEnum(
                    R.styleable.ArtistsView_av_layoutManager,
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
                val springAnimationTraitStatus = getBoolean(R.styleable.ArtistsView_av_springAnimationTraitStatus, false)
                artistAdapter = ArtistAdapter(layout, springAnimationTraitStatus)
                rvItems.adapter = artistAdapter
            } finally {
                recycle()
            }
        }
    }
}