package co.thrivemobile.bpt.info

import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.info.vm.ArticleViewModel
import co.thrivemobile.bpt.repository.Article
import co.thrivemobile.bpt.repository.Repository
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.bpt.util.observeOnce
import co.thrivemobile.networking.MetaData
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class ArticleViewModelTest {

    companion object {
        private const val dummyUrl = "www.dummyurl.com"
    }

    @Mock lateinit var mockRepository: Repository

    private lateinit var viewModel: ArticleViewModel

    @Test
    @DisplayName("When no metadata is returned then show the placeholders")
    fun noMetaData() {
        viewModel = ArticleViewModel(mockRepository) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(Article) -> Unit>()

        verify(mockRepository).getArticleByUrl(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val article = Article()
        callback.invoke(article)

        assertTrue(viewModel.titleLiveData.value == article.metadata.title)
        assertTrue(viewModel.descriptionLiveData.value == article.metadata.description)
        assertTrue(viewModel.imageUrlLiveData.value == article.metadata.imageUrl)
        viewModel.showPlaceholderLiveData.observeOnce {
            assertTrue(it)
        }
    }

    @Test
    @DisplayName("When metadata is returned then don't show the placeholders")
    fun metaDataPresent() {
        viewModel = ArticleViewModel(mockRepository) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(Article) -> Unit>()

        verify(mockRepository).getArticleByUrl(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val metaData = MetaData(
            title = "dummy title",
            description = "dummy description",
            imageUrl = "$dummyUrl/image"
        )
        val article = Article(metaData)
        callback.invoke(article)

        assertTrue(viewModel.titleLiveData.value == metaData.title)
        assertTrue(viewModel.descriptionLiveData.value == metaData.description)
        assertTrue(viewModel.imageUrlLiveData.value == metaData.imageUrl)
        viewModel.showPlaceholderLiveData.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When metadata is returned without a title then don't show the placeholders")
    fun metaDataPresentNoTitle() {
        viewModel = ArticleViewModel(mockRepository) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(Article) -> Unit>()

        verify(mockRepository).getArticleByUrl(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val metaData = MetaData(
            title = "",
            description = "dummy description",
            imageUrl = "$dummyUrl/image"
        )
        val article = Article(metaData)
        callback.invoke(article)

        assertTrue(viewModel.titleLiveData.value == metaData.title)
        assertTrue(viewModel.descriptionLiveData.value == metaData.description)
        assertTrue(viewModel.imageUrlLiveData.value == metaData.imageUrl)
        viewModel.showPlaceholderLiveData.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    @DisplayName("When metadata is returned without a description then don't show the placeholders")
    fun metaDataPresentNoDescription() {
        viewModel = ArticleViewModel(mockRepository) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(Article) -> Unit>()

        verify(mockRepository).getArticleByUrl(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val metaData = MetaData(
            title = "dummy title",
            description = "",
            imageUrl = "$dummyUrl/image"
        )
        val article = Article(metaData)
        callback.invoke(article)

        assertTrue(viewModel.titleLiveData.value == metaData.title)
        assertTrue(viewModel.descriptionLiveData.value == metaData.description)
        assertTrue(viewModel.imageUrlLiveData.value == metaData.imageUrl)
        viewModel.showPlaceholderLiveData.observeOnce {
            assertFalse(it)
        }
    }
}
