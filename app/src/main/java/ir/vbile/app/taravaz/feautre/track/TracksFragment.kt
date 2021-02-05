package ir.vbile.app.taravaz.feautre.track

import android.os.Bundle
import android.view.View
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import kotlinx.android.synthetic.main.base_item_row.view.*
import kotlinx.android.synthetic.main.fragment_track.*
import org.koin.android.viewmodel.ext.android.viewModel

class TracksFragment : TarAvazFragment(R.layout.fragment_track) {
    val vm: TrackVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rowEditorSuggestion.btnViewAll.setOnClickListener {
            longToast("مشاهده همه کلیک شد")
        }
        vm.tracks.observe(viewLifecycleOwner) {
            rowEditorSuggestion.submitList(it)
            rowLatest.submitList(it)
        }
    }
}