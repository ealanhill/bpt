package co.thrivemobile.bpt.info

import co.thrivemobile.bpt.info.vm.ArticleViewModel
import co.thrivemobile.bpt.util.InstantExecutorExtension
import co.thrivemobile.networking.Network
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [ InstantExecutorExtension::class, MockitoExtension::class ])
class ArticleViewModelTest {

    @Mock lateinit var mockNetwork: Network

    private lateinit var viewModel: ArticleViewModel

    @Test
    @DisplayName("When no metadata is returned then show the placeholders")
    fun noMetaData() {
//        `when`(mockNetwork.getMetaData())
    }
}
