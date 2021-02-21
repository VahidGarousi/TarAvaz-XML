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
import ir.vbile.app.taravaz.services.ImageLoadingService
import ir.vbile.app.taravaz.view.cusom.ItemEventListener
import kotlinx.android.synthetic.main.fragment_artist.*
import javax.inject.Inject

@AndroidEntryPoint
class ArtistFragment : TarAvazFragment<ArtistVM>(
    R.layout.fragment_artist,
    ArtistVM::class
), ItemEventListener<Track, Int> {
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
            longToast(it.songUrl)
        }
        rowTracks.setOnItemEventListener(this)
        vm.songs.observe(viewLifecycleOwner) {
            rowTracks.submitList(it)
        }
        btnTracks.setOnClickListener {
            longToast("ترانه ها کلیک شد")
        }
        btnBiography.setOnClickListener {
            longToast("بیوگرافی کلیک شد")
        }
    }

    override fun subscribeToObservers() {
        TODO("Not yet implemented")
    }

    override fun onClick(item: Track, position: Int) {
        val action = ArtistFragmentDirections.actionArtistFragmentToTrackFragment(item)
        findNavController().navigate(action)
    }

    override fun onLongClick(item: Track, position: Int) {
        longToast(item.title)
    }

}