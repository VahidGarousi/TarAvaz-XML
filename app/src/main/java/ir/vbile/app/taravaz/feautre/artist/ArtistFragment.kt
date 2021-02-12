package ir.vbile.app.taravaz.feautre.artist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazFragment
import ir.vbile.app.taravaz.data.Track
import ir.vbile.app.taravaz.feautre.track.TrackVM
import ir.vbile.app.taravaz.services.ImageLoadingService
import ir.vbile.app.taravaz.view.cusom.ItemEventListener
import kotlinx.android.synthetic.main.fragment_artist.*
import javax.inject.Inject

@AndroidEntryPoint
class ArtistFragment : TarAvazFragment<TrackVM>(
    R.layout.fragment_artist,
    TrackVM::class
), ItemEventListener<Track, Int> {
    //    val vm: TrackVM by viewModel {
//        parametersOf(
//            requireActivity().intent.getStringExtra(
//                EXTRA_KEY_DATA
//            ) ?: 1
//        )
//    }
    @Inject
    lateinit var imageLoadingService: ImageLoadingService
    override fun getViewLifecycleOwner(): LifecycleOwner = parentFragment ?: this
    private val args by navArgs<ArtistFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.artist.let {
            tvName.text = it.name
            tvGenre.text = it.genre
            imageLoadingService.load(ivArtist, it.image)
            imageLoadingService.load(ivArtistBackground, it.image)
        }
        rowTracks.setOnMoreEventListener {
            longToast(it.trackAddress)
        }
        rowTracks.setOnItemEventListener(this)
        vm.tracks.observe(viewLifecycleOwner) {
            rowTracks.submitList(it)
        }
        btnTracks.setOnClickListener {
            longToast("ترانه ها کلیک شد")
        }
        btnBiography.setOnClickListener {
            longToast("بیوگرافی کلیک شد")
        }
    }

    override fun onClick(item: Track, position: Int) {
        val action = ArtistFragmentDirections.actionArtistFragmentToPlayerFragment(item)
        findNavController().navigate(action)
    }

    override fun onLongClick(item: Track, position: Int) {
        longToast(item.title)
    }

}