package kr.mojise.network.demo

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kr.mojise.common.network.AppDispatchers
import kr.mojise.common.network.Dispatcher
import kr.mojise.network.JvmUnitTestDemoAssetManager
import kr.mojise.network.NetworkDataSource
import java.io.BufferedReader
import javax.inject.Inject

/**
 * [NiaNetworkDataSource] implementation that provides static news resources to aid development
 */
class DemoNiaNetworkDataSource @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : NetworkDataSource {

    /**
     * Get data from the given JSON [fileName].
     */
    @OptIn(ExperimentalSerializationApi::class)
    private suspend inline fun <reified T> getDataFromJsonFile(fileName: String): List<T> =
        withContext(ioDispatcher) {
            assets.open(fileName).use { inputStream ->
                if (SDK_INT <= M) {
                    /**
                     * On API 23 (M) and below we must use a workaround to avoid an exception being
                     * thrown during deserialization. See:
                     * https://github.com/Kotlin/kotlinx.serialization/issues/2457#issuecomment-1786923342
                     */
                    inputStream.bufferedReader().use(BufferedReader::readText)
                        .let(networkJson::decodeFromString)
                } else {
                    networkJson.decodeFromStream(inputStream)
                }
            }
        }

    override suspend fun ex(ids: List<String>?): List<String> {
        TODO("Not yet implemented")
    }
}