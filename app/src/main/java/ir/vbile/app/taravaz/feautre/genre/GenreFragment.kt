package ir.vbile.app.taravaz.feautre.genre

import android.os.Bundle
import android.view.View
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import kotlinx.android.synthetic.main.fragment_genre.*
import org.koin.android.viewmodel.ext.android.viewModel

class GenreFragment : TarAvazFragment(R.layout.fragment_genre) {
    val vm: GenreVM by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.genres.observe(viewLifecycleOwner) {
            rowGenres.submitList(it)
        }
    }
}