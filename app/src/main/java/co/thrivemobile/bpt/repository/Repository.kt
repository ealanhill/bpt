package co.thrivemobile.bpt.repository

import co.thrivemobile.networking.MetaData
import co.thrivemobile.networking.Network
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.entities.DatabaseArticle

class Repository(private val database: BptDao, private val network: Network) {

    fun getArticleByUrl(url: String): Article {

        val databaseArticle = database.getArticle(url)
        lateinit var article: Article

        if (databaseArticle == null) {
            network.getMetaData(url) { metaData, content ->
                article = processArticleFromNetwork(url, metaData, content)
            }
        } else {
            val metaData = MetaData(
                title = databaseArticle.title,
                description = databaseArticle.description,
                imageUrl = databaseArticle.imageUrl
            )
            article = Article(metaData, databaseArticle.content)
        }

        return article
    }

    private fun processArticleFromNetwork(
        url:String,
        metaData: MetaData,
        content: String
    ): Article {
        val newDatabaseArticle = DatabaseArticle(
            url = url,
            title = metaData.title,
            description = metaData.description,
            imageUrl = metaData.imageUrl,
            content = content
        )
        database.insertArticle(newDatabaseArticle)
        return Article(metaData, content)
    }
}
