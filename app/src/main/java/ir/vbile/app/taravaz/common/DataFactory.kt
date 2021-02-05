package ir.vbile.app.taravaz.common

import io.reactivex.Single
import ir.vbile.app.taravaz.App
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.data.Banner
import ir.vbile.app.taravaz.data.Track
import java.util.*

object DataFactory {
    fun getAllBanners(): Single<List<Banner>> = Single.just(
        listOf(
            Banner(1, "http://project.vbile.ir/taravaz/1.png", 1, ""),
            Banner(1, "http://project.vbile.ir/taravaz/2.png", 1, ""),
            Banner(1, "http://project.vbile.ir/taravaz/3.png", 1, "")
        )
    )

    fun getAllTracks(): List<Track> = listOf(
        Track(
            1,
            App.appContext.getString(R.string.gole_maryam_title),
            App.appContext.getString(R.string.gole_maryam_lyric),
            1,
            300,
            Calendar.getInstance(),
            App.appContext.getString(R.string.gole_maryam_url),
            App.appContext.getString(R.string.genre_love),
            App.appContext.getString(R.string.reza_bahram),
            "http://tabamusic.com/wp-content/uploads/2020/11/Reza-Bahram-Gole-Maryam.jpg"
        ),
        Track(
            2,
            "شهرزاد",
            "بگو چگونه بگذرم از آن نگاه مشرقی\n" +
                    "نیامدی بگو چرا رسیده وقت عاشقی\n" +
                    "\n" +
                    "بیا بیا که بعد تو نفس نمیکشم هنوز\n" +
                    "به قلب خسته ام نگو به پای عشق من بسوز\n" +
                    "\n" +
                    "تو شهرزاد قصه ی هزار و یک شب منی\n" +
                    "تمام دلخوشی من برای زنده بودنی\n" +
                    "\n" +
                    "تکست آهنگ شهرزاد حجت اشرف زاده\n" +
                    "\n" +
                    "در این هوای لعنتی کنار من قدم بزن\n" +
                    "سکوت عصر جمعه را به خاطرم به هم بزن\n" +
                    "\n" +
                    "تمام ناتمام من، من از خیال تو پُرم\n" +
                    "اگرچه دل نمیبری من از تو دل نمیبرم\n" +
                    "\n" +
                    "بیا بیا که بعد تو نفس نمیکشم هنوز\n" +
                    "به قلب خسته ام نگو به پای عشق من بسوز\n" +
                    "\n" +
                    "تو شهرزاد قصه ی هزار و یک شب منی\n" +
                    "تمام دلخوشی من برای زنده بودنی",
            2,
            126,
            Calendar.getInstance(),
            "https://dls.music-fa.com/tagdl/99/Hojat%20Ashrafzade%20-%20Shahrzad%20(320).mp3",
            App.appContext.getString(R.string.genre_love),
            "حجت اشرف زاده",
            "https://lh3.googleusercontent.com/proxy/gvNndGk42p1hl7Q3GJKO2PesttPLyFnBkiYY7tA69Tl8KV4vecTOSvcb-3PMzEluvfn-nz3BAE_hjBM5pG5aJpLcmvqLzH_9T5vKx2z0c4ximR_xWPYuA7NT5cNZAgRMmhxwDjZg"
        ),
        Track(
            3,
            "منو تو",
            App.appContext.getString(R.string.gole_maryam_lyric),
            1,
            138,
            Calendar.getInstance(),
            "https://sv.blogmusic.ir/myahang/Mohsen-Ebrahimzadeh-Mano-To-320.mp3",
            App.appContext.getString(R.string.genre_love),
            "محسن ابراهیم زاده",
            "https://upmusics.com/wp-content/uploads/2019/01/450.jpg"
        ),
        Track(
            4,
            "بیا پیشم",
            "یه دریا و دوتا دل آخ یه آتیش\n" +
                    "\n" +
                    "لب ساحل چشای روشنت\n" +
                    "\n" +
                    "میتابه مثل ماه کامل\n" +
                    "\n" +
                    "نگات میده منو دق\n" +
                    "\n" +
                    "آخ دوتامون توی\n" +
                    "\n" +
                    "قایق امون از تو امون\n" +
                    "\n" +
                    "از من اون از دل عاشق\n" +
                    "\n" +
                    "بیا پیشم حالا یکمی راه بیا تو با ما\n" +
                    "\n" +
                    "انقده خوبی که بردی دلمو هزارجا\n" +
                    "\n" +
                    "چشای تو سخت گرفته قلبم رو هدف گرفته\n" +
                    "\n" +
                    "یکی اومده که این دلمو به حرف گرفته\n" +
                    "\n" +
                    "بهت قول داده بودم دلم واسه تو باشه\n" +
                    "\n" +
                    "من قول و قرارم با تو هر روز سر جاشه\n" +
                    "\n" +
                    "تنها تویی که به دلم میشینه حست واسه\n" +
                    "\n" +
                    "اینه که شیش دنگ دلم خورده به اسمت\n" +
                    "\n" +
                    "بیا پیشم حالا یکمی راه بیا تو با ما\n" +
                    "\n" +
                    "انقده خوبی که بردی دلمو هزارجا\n" +
                    "\n" +
                    "چشای تو سخت گرفته قلبم رو هدف گرفته\n" +
                    "\n" +
                    "یکی اومده که این دلمو به حرف گرفته\n" +
                    "\n" +
                    "بیا پیشم حالا یکمی راه بیا تو با ما\n" +
                    "\n" +
                    "انقده خوبی که بردی دلمو هزارجا\n" +
                    "\n" +
                    "چشای تو سخت گرفته قلبم رو هدف گرفته\n" +
                    "\n" +
                    "یکی اومده که این دلمو به حرف گرفته",
            1,
            58,
            Calendar.getInstance(),
            "https://dl.sedabaran.ir/1399/10/sina_derakhshande_bia_pisham.mp3",
            App.appContext.getString(R.string.genre_love),
            "سینا درخشنده",
            "https://sedabaran.ir/wp-content/webp-express/webp-images/uploads/2021/01/sina_derakhshande_bia_pisham.jpg.webp"
        ),
        Track(
            5,
            "آواره",
            "",
            1,
            45,
            Calendar.getInstance(),
            App.appContext.getString(R.string.gole_maryam_url),
            App.appContext.getString(R.string.genre_love),
            "راغب",
            "https://sedabaran.ir/wp-content/webp-express/webp-images/uploads/2021/01/ragheb_avareh.jpg.webp"
        )
    )
}