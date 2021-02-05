package ir.vbile.app.taravaz.feautre.search

import android.os.Bundle
import android.view.View
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import kotlinx.android.synthetic.main.artist_base_row.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : TarAvazFragment(R.layout.fragment_search) {
    val vm: SearchVM by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rowArtists.btnViewAll.setOnClickListener {
            longToast("مشاهده همه کلیک شد")
        }
        vm.suggestedArtist.observe(viewLifecycleOwner) {
            rowArtists.submitList(it)
        }
    }
}