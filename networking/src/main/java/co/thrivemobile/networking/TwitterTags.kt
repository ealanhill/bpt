package co.thrivemobile.networking

import java.util.regex.Pattern

internal object TwitterTags {
    val TITLE: Pattern = Pattern.compile("<meta name=\"twitter:title\" content=\"([^\"]*)\"")
    val DESCRIPTION: Pattern = Pattern.compile("<meta name=\"twitter:description\" content=\"([^\"]*)\"")
    val IMAGE_URL: Pattern = Pattern.compile("<meta name=\"twitter:image\" content=\"(\\S+)\"")
}
