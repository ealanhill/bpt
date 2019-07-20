package co.thrivemobile.networking

/*
 note: maybe I can use nullable values?

 metaData.imageUrl?.let {
    picasso.load(it)
        .into(imageView)
 }
*/
data class MetaData(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = ""
)
