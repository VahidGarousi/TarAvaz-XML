package ir.vbile.app.taravaz.data.api

data class ArrayResponse<T>(
    val code: Int,
    val data: List<T>,
    val status: String
)
