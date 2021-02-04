package ir.vbile.app.taravaz.common

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Banner

object DataFactory {
    fun getAllBanners(): Single<List<Banner>> = Single.just(
        listOf(
            Banner(1, "http://project.vbile.ir/taravaz/1.png", 1, ""),
            Banner(1, "http://project.vbile.ir/taravaz/2.png", 1, ""),
            Banner(1, "http://project.vbile.ir/taravaz/3.png", 1, "")
        )
    )
}