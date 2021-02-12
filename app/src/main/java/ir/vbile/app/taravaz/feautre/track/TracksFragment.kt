package ir.vbile.app.taravaz.feautre.track

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.EXTRA_KEY_DATA
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.view.cusom.ItemEventListener
import kotlinx.android.synthetic.main.base_track_row.view.*
import kotlinx.android.synthetic.main.fragment_track.*

@AndroidEntryPoint
class TracksFragment : TarAvazFragment<TrackVM>(
    R.layout.fragment_track,
    TrackVM::class
) , ItemEventListener<Track,Int>{

//    val vm: TrackVM by viewModel {
//        parametersOf(
//            requireActivity().intent.getStringExtra(
//                EXTRA_KEY_DATA
//            ) ?: 1
//        )
//    }

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
            rowEditorSuggestion.submitList(it)
            val list = it.toMutableList()
            list.addAll(it)
            list.addAll(it)
            list.addAll(it)
            rowLatest.submitList(list)
        }
    }

    override fun onClick(item: Track, position: Int) {
        val action = TracksFragmentDirections.actionTrackFragmentToPlayerFragment(item)
        findNavController().navigate(action)
    }

    override fun onLongClick(item: Track, position: Int) {
        TODO("Not yet implemented")
    }
}