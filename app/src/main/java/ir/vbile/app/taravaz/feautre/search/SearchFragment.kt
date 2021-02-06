package ir.vbile.app.taravaz.feautre.search

import android.os.Bundle
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.EXTRA_KEY_DATA
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.view.cusom.track.TrackView
import kotlinx.android.synthetic.main.base_artist_row.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SearchFragment : TarAvazFragment(R.layout.fragment_search),TrackView.OnTrackClicked {
    val vm: SearchVM by viewModel {
        parametersOf(
            requireActivity().intent.getStringExtra(
                EXTRA_KEY_DATA
            ) ?: 1
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rowArtists.btnViewAll.setOnClickListener {
            longToast("مشاهده همه کلیک شد")
        }
        rowAlbums.btnViewAll.setOnClickListener {
            longToast("مشاهده همه کلیک شد")
        }
        btnFilter.setOnClickListener {
            longToast("دکمه فیلتر کلیک شد")
        }
        rowLatestTracks.btnViewAll.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setSingleChoiceItems(
                    R.array.sortTitlesArray,
                    vm.sort
                ) { dialogInterface, selectedSortIndex ->
                    vm.onSelectedSortChangeByUseR(selectedSortIndex)
                    dialogInterface.dismiss()
                }
                .setTitle(R.string.sort)
            dialog.show()
        }
        vm.suggestedArtist.observe(viewLifecycleOwner) {
            rowArtists.submitList(it)
        }
        vm.suggestedAlbum.observe(viewLifecycleOwner) {
            rowAlbums.submitList(it)
        }
        vm.suggestedTracks.observe(viewLifecycleOwner) {
            rowLatestTracks.submitList(it)
        }
    }

    override fun onTrackClicked(track: Track) {

    }
}