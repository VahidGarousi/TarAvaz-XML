package ir.vbile.app.taravaz.feautre.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.data.repo.TrackRepository
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.launch

class TrackVM constructor(
    private val trackRemoteRepository: TrackRepository
) : TarAvazViewModel() {


    init {
        getTracks()
    }

    private val _tracks: MutableLiveData<List<Track>> = MutableLiveData()
    val tracks: LiveData<List<Track>> = _tracks

    private fun getTracks() {
        viewModelScope.launch {
            trackRemoteRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { tracks ->
                    _tracks.postValue(tracks)
                }
        }
    }
}