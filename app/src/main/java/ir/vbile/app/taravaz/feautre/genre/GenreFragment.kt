package ir.vbile.app.taravaz.feautre.genre

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import kotlinx.android.synthetic.main.fragment_genre.*

@AndroidEntryPoint
class GenreFragment : TarAvazFragment<GenreVM>(
    R.layout.fragment_genre,
    GenreVM::class
) {
    //    val vm: GenreVM by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.genres.observe(viewLifecycleOwner) {
            rowGenres.submitList(it)
        }
    }
    override fun subscribeToObservers() {
    }
}