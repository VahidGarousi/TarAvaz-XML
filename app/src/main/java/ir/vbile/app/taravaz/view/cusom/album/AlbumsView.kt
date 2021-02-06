package ir.vbile.app.taravaz.view.cusom.album

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.data.Album
import ir.vbile.app.taravaz.extentions.getEnum
import ir.vbile.app.taravaz.view.cusom.artist.ArtistAdapter
import ir.vbile.app.taravaz.view.cusom.track.BirLayoutManager
import ir.vbile.app.taravaz.view.cusom.track.BirOrientation
import kotlinx.android.synthetic.main.base_artist_row.view.*

class AlbumsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val albumAdapter: AlbumAdapter

    fun submitList(it: List<Album>) {
        albumAdapter.submitList(it)
    }

    init {
        inflate(context, R.layout.base_album_row, this)
        context.obtainStyledAttributes(attrs, R.styleable.AlbumsView).apply {
            try {
                val title = getString(R.styleable.AlbumsView_album_rowTitle)
                val btnViewAllTitle = getString(R.styleable.AlbumsView_album_viewAllText)
                btnViewAll?.let {
                    it.text = btnViewAllTitle ?: context.getString(R.string.viewAll)
                }
                tvRowTitle?.let {
                    it.text = title ?: context.getString(R.string.latest_albums)
                }
                val layout =
                    getResourceId(R.styleable.AlbumsView_album_viewType, R.layout.item_track_type1)
                val orientation =
                    getEnum(R.styleable.AlbumsView_album_orientation, BirOrientation.Vertical)
                val layoutManager = when (getEnum(
                    R.styleable.AlbumsView_album_layoutManager,
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
                val springAnimationTraitStatus = getBoolean(R.styleable.AlbumsView_album_springAnimationTraitStatus, false)
                albumAdapter = AlbumAdapter(layout, springAnimationTraitStatus)
                rvItems.adapter = albumAdapter
            } finally {
                recycle()
            }
        }
    }
}