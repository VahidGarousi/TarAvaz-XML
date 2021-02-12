package ir.vbile.app.taravaz.feautre.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Genre
import ir.vbile.app.taravaz.data.repo.GenreRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreVM @Inject constructor(
    private val genreRepository: GenreRepository
) : TarAvazViewModel() {
    private val _genres: MutableLiveData<List<Genre>> = MutableLiveData()
    val genres: LiveData<List<Genre>> = _genres

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            genreRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { genres ->
                    _genres.postValue(genres)
                }
        }
    }
}