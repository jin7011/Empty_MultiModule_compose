package kr.mojise.network.retrofit

import androidx.tracing.trace
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kr.mojise.network.NetworkDataSource
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = ""//BuildConfig.BACKEND_URL

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

@Singleton
internal class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : NetworkDataSource {

    private val networkApi = trace("RetrofitNiaNetwork") {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitNetwork::class.java)
    }

    override suspend fun ex(ids: List<String>?): List<String> {
        TODO("Not yet implemented")
    }
}
