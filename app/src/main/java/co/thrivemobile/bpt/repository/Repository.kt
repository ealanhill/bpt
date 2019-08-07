package co.thrivemobile.bpt.repository

import co.thrivemobile.networking.MetaData
import co.thrivemobile.networking.Network
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.entities.DatabaseArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Repository(
    ioDispatcher: CoroutineDispatcher,
    private val database: BptDao,
    private val network: Network
) {

    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(ioDispatcher + job)

    fun getArticleByUrl(url: String, onResult: (Article) -> Unit) {
        val databaseArticle = runBlocking { database.getArticle(url) }

        if (databaseArticle == null) {
            network.getMetaData(url) { metaData, content ->
                onResult(processArticleFromNetwork(url, metaData, content))
            }
        } else {
            val metaData = MetaData(
                title = databaseArticle.title,
                description = databaseArticle.description,
                imageUrl = databaseArticle.imageUrl
            )
            onResult(Article(metaData, databaseArticle.content))
        }
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
        ioScope.launch { database.insertArticle(newDatabaseArticle) }
        return Article(metaData, content)
    }
}
