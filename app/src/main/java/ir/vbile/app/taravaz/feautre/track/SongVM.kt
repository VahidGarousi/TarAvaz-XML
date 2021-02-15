package ir.vbile.app.taravaz.feautre.track

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.taravaz.App
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.Constants.MEDIA_ROOT_ID
import ir.vbile.app.taravaz.common.Constants.UPDATE_PLAYER_POSITION_INTERVAL
import ir.vbile.app.taravaz.common.DataFactory
import ir.vbile.app.taravaz.common.TarAvazViewModel
import ir.vbile.app.taravaz.data.Song
import ir.vbile.app.taravaz.data.repo.SongRepository
import ir.vbile.app.taravaz.exoplayer.*
import ir.vbile.app.taravaz.extentions.asyncNetworkRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SongVM @Inject constructor(
    private val songRemoteRepository: SongRepository
    ) : TarAvazViewModel() {
    var sort: Int = 1
    val selectedSortTitle: MutableLiveData<Int> = MutableLiveData()
    val sortTitles = arrayOf(
        R.string.sortLatest,
        R.string.sortPopular,
        R.string.sortPriceHighToLow,
        R.string.sortPriceLowToHigh
    )
    private val _tracks: MutableLiveData<List<Song>> = MutableLiveData()
    val tracks: LiveData<List<Song>> = _tracks

    private fun getTracks(sort: Int) {
        viewModelScope.launch {
            songRemoteRepository.getAll()
                .asyncNetworkRequest()
                .subscribe { tracks ->
                    _tracks.postValue(tracks)
                }
        }
    }

    fun onSelectedSortChangeByUseR(sort: Int) {
        this.sort = sort
        selectedSortTitle.value = sortTitles[sort]
        getTracks(sort)
    }

}