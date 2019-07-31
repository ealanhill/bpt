package co.thrivemobile.bpt.info

import co.thrivemobile.bpt.info.items.ArticleItem
import co.thrivemobile.bpt.info.vm.ArticleViewModel
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.bpt.util.observeOnce
import co.thrivemobile.networking.MetaData
import co.thrivemobile.networking.Network
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class ArticleViewModelTest {

    companion object {
        private const val dummyUrl = "www.dummyurl.com"
    }

    @Mock lateinit var mockNetwork: Network

    private lateinit var viewModel: ArticleViewModel

    @Test
    @DisplayName("When no metadata is returned then show the placeholders")
    fun noMetaData() {
        viewModel = ArticleViewModel(mockNetwork) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(MetaData) -> Unit>()

        verify(mockNetwork).getMetaData(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val metaData = MetaData()
        callback.invoke(metaData)

        assertTrue(viewModel.titleLiveData.value == metaData.title)
        assertTrue(viewModel.descriptionLiveData.value == metaData.description)
        assertTrue(viewModel.imageUrlLiveData.value == metaData.imageUrl)
        viewModel.showPlaceholderLiveData.observeOnce {
            assertTrue(it)
        }
    }

    @Test
    @DisplayName("When metadata is returned then don't show the placeholders")
    fun metaDataPresent() {
        viewModel = ArticleViewModel(mockNetwork) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(MetaData) -> Unit>()

        verify(mockNetwork).getMetaData(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val metaData = MetaData(
            title = "dummy title",
            description = "dummy description",
            imageUrl = "$dummyUrl/image"
        )
        callback.invoke(metaData)

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
        viewModel = ArticleViewModel(mockNetwork) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(MetaData) -> Unit>()

        verify(mockNetwork).getMetaData(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val metaData = MetaData(
            title = "",
            description = "dummy description",
            imageUrl = "$dummyUrl/image"
        )
        callback.invoke(metaData)

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
        viewModel = ArticleViewModel(mockNetwork) { it }
        viewModel.loadItem(ArticleItem(dummyUrl))
        val callbackCaptor = argumentCaptor<(MetaData) -> Unit>()

        verify(mockNetwork).getMetaData(eq(dummyUrl), callbackCaptor.capture())

        val callback = callbackCaptor.firstValue
        val metaData = MetaData(
            title = "dummy title",
            description = "",
            imageUrl = "$dummyUrl/image"
        )
        callback.invoke(metaData)

        assertTrue(viewModel.titleLiveData.value == metaData.title)
        assertTrue(viewModel.descriptionLiveData.value == metaData.description)
        assertTrue(viewModel.imageUrlLiveData.value == metaData.imageUrl)
        viewModel.showPlaceholderLiveData.observeOnce {
            assertFalse(it)
        }
    }
}
