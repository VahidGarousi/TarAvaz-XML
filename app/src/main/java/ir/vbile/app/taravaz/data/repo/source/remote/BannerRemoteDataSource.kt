package ir.vbile.app.taravaz.data.repo.source.remote

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.data.repo.source.BannerDataSource
import ir.vbile.app.taravaz.services.http.StartupApi
import javax.inject.Inject

class BannerRemoteDataSource @Inject constructor(
    private val startupApi: StartupApi
) : BannerDataSource {
    override fun getAll(): Single<List<Banner>> =
        startupApi.getBanners()
}