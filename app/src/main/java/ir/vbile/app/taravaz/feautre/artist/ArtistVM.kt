package ir.vbile.app.taravaz.feautre.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Artist
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.repo.ArtistRepository
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistVM @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val songRepository: SongRepository
) : TarAvazViewModel() {
    private val _artists: MutableLiveData<List<Artist>> = MutableLiveData()
    val artists: LiveData<List<Artist>> = _artists

    init {
        getArtists()
        getSongs()
    }

    private fun getArtists() {
        viewModelScope.launch {
            artistRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { artists ->
                    _artists.postValue(artists)
                }
        }
    }

    private val _songs: MutableLiveData<List<Track>> = MutableLiveData()
    val songs: LiveData<List<Track>> = _songs
    private fun getSongs() {
        viewModelScope.launch {
            songRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { songs ->
                    _songs.postValue(songs)
                }
        }
    }
}