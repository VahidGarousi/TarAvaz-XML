package ir.vbile.app.taravaz.exoplayer

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.Nullable
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import ir.vbile.app.taravaz.App
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.Constants.NOTIFICATION_CHANNEL_ID
import ir.vbile.app.taravaz.common.Constants.NOTIFICATION_ID
import timber.log.Timber


class MusicNotificationManager(
    private val context: Context,
    sessionToken: MediaSessionCompat.Token,
    notificationListener: PlayerNotificationManager.NotificationListener,
    private val newSongCallback: () -> Unit
) {

    fun showNotification(player: Player) {
        notificationManager.setPlayer(player)
    }

    private val notificationManager: PlayerNotificationManager

    init {
        val mediaController = MediaControllerCompat(context, sessionToken)
        notificationManager = PlayerNotificationManager.createWithNotificationChannel(
            context,
            NOTIFICATION_CHANNEL_ID,
            R.string.notification_channel_name,
            R.string.notification_description,
            NOTIFICATION_ID,
            DescriptionAdapter(mediaController),
            notificationListener
        ).apply {
            setSmallIcon(R.drawable.ic_music)
            setMediaSessionToken(sessionToken)
        }
    }

    private inner class DescriptionAdapter(
        private val mediaController: MediaControllerCompat
    ) : PlayerNotificationManager.MediaDescriptionAdapter {
        override fun getCurrentContentTitle(player: Player): CharSequence {
            newSongCallback()
            return mediaController.metadata.description.title.toString()
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            return mediaController.sessionActivity
        }

        override fun getCurrentContentText(player: Player): CharSequence? {
            return mediaController.metadata.description.subtitle.toString()
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            Timber.i("")
            try {
                // To get image using Fresco
                val imageRequest = ImageRequestBuilder
                    .newBuilderWithSource(mediaController.metadata.description.iconUri)
                    .setProgressiveRenderingEnabled(true)
                    .build()
                val imagePipeline = Fresco.getImagePipeline()
                val dataSource = imagePipeline.fetchDecodedImage(imageRequest, App.appContext)

                dataSource.subscribe(object : BaseBitmapDataSubscriber() {
                    override fun onNewResultImpl(@Nullable bitmap: Bitmap?) {
                        // You can use the bitmap in only limited ways
                        // No need to do any cleanup.
                        bitmap?.let {
                            callback.onBitmap(it)
                        }
                    }
                    override fun onFailureImpl(dataSource: DataSource<CloseableReference<CloseableImage>>?) {
                        TODO("Not yet implemented")
                    }
                }, CallerThreadExecutor.getInstance())
            }catch (exp : Exception){
                exp.printStackTrace()
            }
            return null
        }
    }

}