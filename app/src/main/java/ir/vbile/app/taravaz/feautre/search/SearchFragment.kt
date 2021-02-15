package ir.vbile.app.taravaz.feautre.search

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Song
import ir.vbile.app.taravaz.view.cusom.ItemEventListener
import kotlinx.android.synthetic.main.base_artist_row.view.*
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : TarAvazFragment<SearchVM>(
    R.layout.fragment_search,
    SearchVM::class
), ItemEventListener<Song, Int> {
//    val vm: SearchVM by viewModel {
//        parametersOf(
//            requireActivity().intent.getStringExtra(
//                EXTRA_KEY_DATA
//            ) ?: 1
//        )
//    }
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

    override fun onClick(item: Song, position: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToPlayerFragment(item)
        findNavController().navigate(action)
    }

    override fun onLongClick(item: Song, position: Int) {
        TODO("Not yet implemented")
    }
}