package co.thrivemobile.bpt.repository

import co.thrivemobile.networking.MetaData

data class Article(val metadata: MetaData = MetaData(), val content: String = "")
