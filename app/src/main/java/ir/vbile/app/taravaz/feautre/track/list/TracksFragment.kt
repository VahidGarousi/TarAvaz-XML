package ir.vbile.app.taravaz.feautre.track.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.feautre.main.MainVM
import ir.vbile.app.taravaz.feautre.track.TrackVM
import ir.vbile.app.taravaz.view.cusom.ItemEventListener
import kotlinx.android.synthetic.main.base_track_row.view.*
import kotlinx.android.synthetic.main.fragment_tracks.*

@AndroidEntryPoint
class TracksFragment : TarAvazFragment<TrackVM>(
    R.layout.fragment_tracks,
    TrackVM::class
), ItemEventListener<Track, Int> {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rowEditorSuggestion.btnViewAll.setOnClickListener {
            longToast("مشاهده همه کلیک شد")
        }
        rowLatest.btnViewAll.setOnClickListener {
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
        vm.tracks.observe(viewLifecycleOwner) {
            it?.let {
                rowEditorSuggestion.submitList(it)
                val list = it.toMutableList()
                list.addAll(it)
                list.addAll(it)
                list.addAll(it)
                rowLatest.submitList(list)
            }
        }
    }
    override fun subscribeToObservers() {
    }
    override fun onClick(item: Track, position: Int) {
        val action = TracksFragmentDirections.actionTrackFragmentToFragmentTrack(item)
        findNavController().navigate(action)
    }

    override fun onLongClick(item: Track, position: Int) {
    }
}