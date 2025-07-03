package kr.mojise.datastore

import androidx.datastore.core.DataStore
import kr.mojise.default_compose.core.datastore.ExampleProtoObject
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<ExampleProtoObject>,
) {
    val userData = userPreferences.data
//        .map {
//        }
}