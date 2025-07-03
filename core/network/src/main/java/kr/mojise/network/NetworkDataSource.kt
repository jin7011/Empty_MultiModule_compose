package kr.mojise.network

interface NetworkDataSource {
    suspend fun ex(ids: List<String>? = null): List<String>
}
