package ir.vbile.app.taravaz.services.http

import io.reactivex.Single
import ir.vbile.app.taravaz.data.Banner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("banner/slider")
    fun getBanners(): Single<List<Banner>>
}


fun createApiServiceInstance(): ApiService {
    val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val oldRequest = it.request()
            val newRequestBuilder = oldRequest.newBuilder()
            newRequestBuilder.addHeader("Accept", "application/json")
            newRequestBuilder.method(oldRequest.method, oldRequest.body)
            return@addInterceptor it.proceed(newRequestBuilder.build())
        }.addInterceptor(logging)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("http://expertdevelopers.ir/api/v1/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}