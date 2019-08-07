package co.thrivemobile.bpt.repository

import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.networking.MetaData
import co.thrivemobile.networking.Network
import co.thrivemobile.repository.BptDao
import co.thrivemobile.repository.entities.DatabaseArticle
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class RepositoryTest {

    @Mock private lateinit var mockNetwork: Network
    private val testDispatcher = Dispatchers.Unconfined

    companion object {
        private const val TEST_TITLE = "DummyTitle"
        private const val TEST_URL = "dummyurl.com"
        private const val TEST_DESCRIPTION = "Dummy Description"
        private const val TEST_IMAGE_URL = "$TEST_URL/image"
        private const val TEST_CONTENT = "Dummy Content"
        private val testDatabaseArticle = DatabaseArticle(
            url = TEST_URL,
            title = TEST_TITLE,
            description = TEST_DESCRIPTION,
            imageUrl = TEST_IMAGE_URL,
            content = TEST_CONTENT
        )
        private val testMetaData = MetaData(
            title = TEST_TITLE,
            description = TEST_DESCRIPTION,
            imageUrl = TEST_IMAGE_URL
        )
    }

    @Test
    @DisplayName("When there is an article in the database then return that information")
    fun articleInDatabase() {
        val mockDao = mock<BptDao> {
            onBlocking { getArticle(eq(TEST_URL)) } doReturn testDatabaseArticle
        }
        val repository = Repository(testDispatcher, mockDao, mockNetwork)

        repository.getArticleByUrl(TEST_URL) { article ->
            val metadata = article.metadata
            assertTrue(metadata.title == testDatabaseArticle.title)
            assertTrue(metadata.description == testDatabaseArticle.description)
            assertTrue(metadata.imageUrl == testDatabaseArticle.imageUrl)
            assertTrue(article.content == testDatabaseArticle.content)
        }
    }

    @Test
    @DisplayName("When there is no article in the database and the network doesn't return on then return an empty article")
    fun noArticle() {
        val mockDao = mock<BptDao> {
            onBlocking { getArticle(eq(TEST_URL)) } doReturn null
        }

        val repository = Repository(testDispatcher, mockDao, mockNetwork)
        repository.getArticleByUrl(TEST_URL) { article ->
            val metaData = article.metadata
            assertNotNull(article)
            assertTrue(metaData.title.isEmpty())
            assertTrue(metaData.description.isEmpty())
            assertTrue(metaData.imageUrl.isEmpty())
            assertTrue(article.content.isEmpty())
        }

        val callbackCaptor = argumentCaptor<(MetaData, String) -> Unit>()
        verify(mockNetwork).getMetaData(anyString(), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        callback.invoke(MetaData(), "")
    }

    @Test
    @DisplayName("When there is no article in the database but the network returns one ensure it is returned and saved by the repository")
    fun articleFromNetwork() {
        val mockDao = mock<BptDao> {
            onBlocking { getArticle(eq(TEST_URL)) } doReturn null
        }
        val repository = Repository(testDispatcher, mockDao, mockNetwork)
        repository.getArticleByUrl(TEST_URL) { article ->
            val metaData = article.metadata
            assertNotNull(article)
            assertTrue(metaData.title == TEST_TITLE)
            assertTrue(metaData.description == TEST_DESCRIPTION)
            assertTrue(metaData.imageUrl == TEST_IMAGE_URL)
            assertTrue(article.content == TEST_CONTENT)
        }

        val callbackCaptor = argumentCaptor<(MetaData, String) -> Unit>()
        verify(mockNetwork).getMetaData(anyString(), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        callback.invoke(testMetaData, TEST_CONTENT)

        runBlocking { verify(mockDao).insertArticle(testDatabaseArticle) }
    }
}
