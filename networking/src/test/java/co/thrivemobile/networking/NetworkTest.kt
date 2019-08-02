package co.thrivemobile.networking

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
}
