package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track

interface TrackDataSource {
    fun getAll() : Single<List<Track>>
}