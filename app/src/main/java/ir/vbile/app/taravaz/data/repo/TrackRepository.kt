package ir.vbile.app.taravaz.data.repo

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Track

interface TrackRepository {
    fun getAll(): Single<List<Track>>
}