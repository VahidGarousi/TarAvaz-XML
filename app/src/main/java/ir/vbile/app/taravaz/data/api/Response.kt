package ir.vbile.app.taravaz.data.api

data class Response<T>(
    val code: Int,
    val data: T,
    val status: String
)
