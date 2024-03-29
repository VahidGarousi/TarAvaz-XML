package ir.vbile.app.taravaz.data.repo.demo

import io.reactivex.Single
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.repo.SongRepository

class DemoSongRepositoryImpl : SongRepository {
    override fun getAll(): Single<List<Track>> {
        return Single.just(
            DataFactory.getAllTracks()
        )
    }

    override fun getAll(artistId: Int): Single<List<Track>> {
        return Single.just(
            DataFactory.getAllTracks()
        )
    }
}