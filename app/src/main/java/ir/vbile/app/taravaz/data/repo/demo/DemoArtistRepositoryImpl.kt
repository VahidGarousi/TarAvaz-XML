package ir.vbile.app.taravaz.data.repo.demo

import io.reactivex.Single
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.repo.ArtistRepository

class DemoArtistRepositoryImpl : ArtistRepository {
    override fun getAll(): Single<List<Artist>> {
        return Single.just(
            DataFactory.getSuggestedArtist()
        )
    }
}