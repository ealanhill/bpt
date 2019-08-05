package co.thrivemobile.networking

import co.thrivemobile.networking.data.SampleData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@ExtendWith(value = [ MockitoExtension::class ] )
class NetworkTest {

    companion object {
        private const val TITLE = "Find Your Most Productive Time of the Day by Tracking Your Body&#039;s Energy Levels"
        private const val DESCRIPTION = "Everyone has their own time of the day when their energy levels peak - if you can find yours, you can schedule your best work at that time. Here&#039;s how."
        private const val IMAGE = "https://collegeinfogeek.com/wp-content/uploads/2016/06/share-4.jpg"
    }

    @Mock lateinit var mockClient: OkHttpClient
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var network: Network

    @Test
    @DisplayName("When request comes back empty then we get default metadata")
    fun emptyNetwork() {
        network = Network(
            testDispatcher,
            testDispatcher,
            mockClient
        )
        val expectedMetaData = MetaData()

        val mockResponse: Response = mock()
        whenever(mockResponse.body).thenAnswer { "".toResponseBody() }

        val mockCall: Call = mock()
        whenever(mockCall.execute()).thenAnswer {
            mockResponse
        }

        whenever(mockClient.newCall(any())).thenAnswer {
            mockCall
        }
        network.getMetaData("https://www.dummyurl.com") {
            assertTrue(it == expectedMetaData)
        }
    }

    @Test
    @DisplayName("When request returns data with full metadata then we get the meatadata")
    fun correctMetadata() {
        network = Network(
            testDispatcher,
            testDispatcher,
            mockClient
        )
        val expectedMetaData = MetaData(
            title = TITLE,
            description = DESCRIPTION,
            imageUrl = IMAGE
        )

        val mockResponse: Response = mock()
        whenever(mockResponse.body).thenAnswer { SampleData.WEBPAGE_WITH_TAGS.toResponseBody() }

        val mockCall: Call = mock()
        whenever(mockCall.execute()).thenAnswer {
            mockResponse
        }

        whenever(mockClient.newCall(any())).thenAnswer {
            mockCall
        }
        network.getMetaData("https://www.dummyurl.com") {
            assertTrue(it == expectedMetaData)
        }
    }

    @Test
    @DisplayName("When request returns data without the title then we get the correct metadata")
    fun correctMetadataWithoutTitle() {
        network = Network(
            testDispatcher,
            testDispatcher,
            mockClient
        )
        val expectedMetaData = MetaData(
            description = DESCRIPTION,
            imageUrl = IMAGE
        )

        val mockResponse: Response = mock()
        whenever(mockResponse.body).thenAnswer { SampleData.WEBPAGE_WITHOUT_TITLE.toResponseBody() }

        val mockCall: Call = mock()
        whenever(mockCall.execute()).thenAnswer {
            mockResponse
        }

        whenever(mockClient.newCall(any())).thenAnswer {
            mockCall
        }
        network.getMetaData("https://www.dummyurl.com") {
            assertTrue(it == expectedMetaData)
        }
    }

    @Test
    @DisplayName("When request returns data without the description then we get the correct metadata")
    fun correctMetadataWithoutDescription() {
        network = Network(
            testDispatcher,
            testDispatcher,
            mockClient
        )
        val expectedMetaData = MetaData(
            title = TITLE,
            imageUrl = IMAGE
        )

        val mockResponse: Response = mock()
        whenever(mockResponse.body).thenAnswer { SampleData.WEBPAGE_WITHOUT_DESCRIPTIONS.toResponseBody() }

        val mockCall: Call = mock()
        whenever(mockCall.execute()).thenAnswer {
            mockResponse
        }

        whenever(mockClient.newCall(any())).thenAnswer {
            mockCall
        }
        network.getMetaData("https://www.dummyurl.com") {
            assertTrue(it == expectedMetaData)
        }
    }

    @Test
    @DisplayName("When request returns data without the image then we get the correct metadata")
    fun correctMetadataWithoutImage() {
        network = Network(
            testDispatcher,
            testDispatcher,
            mockClient
        )
        val expectedMetaData = MetaData(
            title = TITLE,
            description = DESCRIPTION
        )

        val mockResponse: Response = mock()
        whenever(mockResponse.body).thenAnswer { SampleData.WEBPAGE_WITHOUT_IMAGE.toResponseBody() }

        val mockCall: Call = mock()
        whenever(mockCall.execute()).thenAnswer {
            mockResponse
        }

        whenever(mockClient.newCall(any())).thenAnswer {
            mockCall
        }
        network.getMetaData("https://www.dummyurl.com") {
            assertTrue(it == expectedMetaData)
        }
    }

    @Test
    @DisplayName("When request returns data without metadata then we get the correct metadata")
    fun correctMetadataWithoutMetadata() {
        network = Network(
            testDispatcher,
            testDispatcher,
            mockClient
        )
        val expectedMetaData = MetaData()

        val mockResponse: Response = mock()
        whenever(mockResponse.body).thenAnswer { SampleData.WEBPAGE_WITHOUT_METADATA.toResponseBody() }

        val mockCall: Call = mock()
        whenever(mockCall.execute()).thenAnswer {
            mockResponse
        }

        whenever(mockClient.newCall(any())).thenAnswer {
            mockCall
        }
        network.getMetaData("https://www.dummyurl.com") {
            assertTrue(it == expectedMetaData)
        }
    }
}
