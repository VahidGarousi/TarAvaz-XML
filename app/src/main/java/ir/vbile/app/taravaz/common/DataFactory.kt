package ir.vbile.app.taravaz.common

import io.reactivex.Single
import ir.vbile.app.taravaz.App
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.data.*
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
            1.toString(),
            App.appContext.getString(R.string.gole_maryam_title),
            App.appContext.getString(R.string.gole_maryam_lyric),
            1,
            300,
            Calendar.getInstance(),
            App.appContext.getString(R.string.gole_maryam_url),
            App.appContext.getString(R.string.genre_love),
            App.appContext.getString(R.string.reza_bahram),
            "http://tabamusic.com/wp-content/uploads/2020/11/Reza-Bahram-Gole-Maryam.jpg"
        ,getSuggestedArtist()[0]),
        Track(
            2.toString(),
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
            "https://lh3.googleusercontent.com/proxy/gvNndGk42p1hl7Q3GJKO2PesttPLyFnBkiYY7tA69Tl8KV4vecTOSvcb-3PMzEluvfn-nz3BAE_hjBM5pG5aJpLcmvqLzH_9T5vKx2z0c4ximR_xWPYuA7NT5cNZAgRMmhxwDjZg",
            getSuggestedArtist()[3]),
        Track(
            3.toString(),
            "منو تو",
            App.appContext.getString(R.string.gole_maryam_lyric),
            1,
            138,
            Calendar.getInstance(),
            "https://sv.blogmusic.ir/myahang/Mohsen-Ebrahimzadeh-Mano-To-320.mp3",
            App.appContext.getString(R.string.genre_love),
            "محسن ابراهیم زاده",
            "https://upmusics.com/wp-content/uploads/2019/01/450.jpg"
        ,getSuggestedArtist()[1]),
        Track(
            4.toString(),
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
        ,getSuggestedArtist()[2]),
        Track(
            5.toString(),
            "آواره",
            "",
            1,
            45,
            Calendar.getInstance(),
            App.appContext.getString(R.string.gole_maryam_url),
            App.appContext.getString(R.string.genre_love),
            "راغب",
            "https://sedabaran.ir/wp-content/webp-express/webp-images/uploads/2021/01/ragheb_avareh.jpg.webp"
        ,getSuggestedArtist()[3])
    )

    fun getSuggestedArtist(): List<Artist> = listOf(
        Artist(
            1,
            "رضا بهرام",
            "https://smusic.ir/wp-content/uploads/2019/03/Reza-Bahram-Shabhaye-bad.jpg",
            "عاشقانه"
        ),
        Artist(
            2,
            "حجت اشرف زاده",
            "https://www.parsnaz.com/images/2019/12/864598060-parsnaz-com.jpg",
            "عاشقانه"
        ),
        Artist(
            3,
            "محسن ابراهیم زاده",
            "https://files.namnak.com/users/fm/aup/202008/579_pics/%D9%85%D8%AD%D8%B3%D9%86-%D8%A7%D8%A8%D8%B1%D8%A7%D9%87%DB%8C%D9%85-%D8%B2%D8%A7%D8%AF%D9%87.jpg",
            "عاشقانه"
        ),
        Artist(
            4,
            "راغب",
            "https://saednews.com/storage/media-center/images/ac-image-jk1599639239rx.jpeg",
            "عاشقانه"
        ),
        Artist(
            5,
            "محسن چاوشی",
            "https://cdn.yjc.ir/files/fa/news/1398/9/23/11029114_247.jpg",
            "عاشقانه"
        ),
        Artist(6, "محسن یگانه", "https://dl.fars2nes.com/mohsen%20yeganeh/yeganeh.jpg", "عاشقانه"),
        Artist(
            7,
            "فرزاد فرزین",
            "http://sv2.mybia2music.com/s2/Music/1399/07/13/02/Farzad%20Farzin%20-%20Jaye%20To%20Khaliye.jpg",
            "عاشقانه"
        ),
        Artist(
            8,
            "گرشا رضایی",
            "https://pbs.twimg.com/profile_images/1246537718735265792/YDO2BHoo_400x400.jpg",
            "عاشقانه"
        ),
        Artist(
            9,
            "ستین",
            "https://www.beytoote.com/images/stories/art/singer-stein02-4.jpg",
            "عاشقانه"
        ),
        Artist(
            10,
            "احلام",
            "https://photokade.com/wp-content/uploads/ahlammusic-photokade-com-6.jpg",
            "عاشقانه"
        ),
        Artist(
            11,
            "شادمهر عقیلی",
            "https://i.scdn.co/image/b33199dca20bc7118600ac6ab465d3d7ecf78f96",
            "عاشقانه"
        ),
    )

    fun getSuggestedAlbums(): List<Album> = listOf(
        Album(
            1,
            "رگ خواب",
            Calendar.getInstance(),
            "توضیحات آلبوم",
            "https://upload.wikimedia.org/wikipedia/fa/0/05/Rage_Khab.jpg",
            getSuggestedArtist()[0]
        ),
        Album(
            2,
            "شانزلیزه",
            Calendar.getInstance(),
            "توضیحات آلبوم",
            "https://upload.samfuni.com/2019/03/a3_4qe304_2019-03-07-12-39-05_W250_H250.jpg",
            getSuggestedArtist()[1]
        ),
        Album(
            3,
            "مثل مجسمه",
            Calendar.getInstance(),
            "توضیحات آلبوم",
            "https://upload.wikimedia.org/wikipedia/fa/a/a8/Mehdi-Yarrahi-Mesle-Mojasame.jpg",
            getSuggestedArtist()[2]
        ),
        Album(
            4,
            "نگاه",
            Calendar.getInstance(),
            "توضیحات آلبوم",
            "https://upmusics.com/wp-content/uploads/2018/01/143123605962769954ed98a7874136b22.jpg",
            getSuggestedArtist()[3]
        ),
        Album(
            4,
            "اینجانب",
            Calendar.getInstance(),
            "توضیحات آلبوم",
            "https://lh3.googleusercontent.com/proxy/mJyElkPLXbVb0R4RoFhOjgYYkdNzDFIuQYSz3KVCoUaEL-ks1zwnvFMhxaTkKtuS3ORgWnB2pljpXLG982lGjcqnaTKgrEIURkJmNgMNNfY75FqQhZM1qDL4PtsdSVlH3g",
            getSuggestedArtist()[4]
        ),
    )

    fun getAllGenres(): List<Genre> = listOf(
        Genre(1, "پاپ", "", 603),
        Genre(2, "سنتی", "", 603),
        Genre(3, "رپ", "", 603),
        Genre(4, "غمگین", "", 603),
        Genre(5, "پرطرفدار", "", 603),
        Genre(6, "عروسی", "", 603),
        Genre(7, "ماشین", "", 603),
        Genre(8, "محرم", "", 603),
        Genre(9, "فاطمیه", "", 603),
        Genre(10, "شب های قدر", "", 603),
        Genre(11, "پاپ", "", 603),
        Genre(12, "پاپ", "", 603),
        Genre(13, "پاپ", "", 603),
        Genre(14, "پاپ", "", 603),
        Genre(15, "پاپ", "", 603),
        Genre(16, "پاپ", "", 603),
        Genre(17, "پاپ", "", 603),
        Genre(18, "پاپ", "", 603),
        Genre(19, "پاپ", "", 603),
        Genre(20, "پاپ", "", 603),
        Genre(21, "پاپ", "", 603),
        Genre(22, "پاپ", "", 603),
        Genre(23, "پاپ", "", 603),
        Genre(24, "پاپ", "", 603),
        Genre(25, "پاپ", "", 603),
        Genre(26, "پاپ", "", 603),
    )
}