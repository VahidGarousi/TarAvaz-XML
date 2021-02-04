package ir.vbile.app.taravaz.data.repo.source

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.services.http.ApiService

class BannerRemoteDataSource constructor(
    private val apiService: ApiService
) : BannerDataSource {
    override fun getAll(): Single<List<Banner>> =
        apiService.getBanners()
}