/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.mojise.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kr.mojise.common.network.AppDispatchers
import kr.mojise.common.network.Dispatcher
import kr.mojise.common.network.di.ApplicationScope
import kr.mojise.datastore.ExampleProtoObjectPreferencesSerializer
import kr.mojise.default_compose.core.datastore.ExampleProtoObject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    internal fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        exampleProtoObjectSerializer: ExampleProtoObjectPreferencesSerializer,
    ): DataStore<ExampleProtoObject> =
        DataStoreFactory.create(
            serializer = exampleProtoObjectSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
//            migrations = listOf(),
        ) {
            context.dataStoreFile("user_preferences.pb")
        }
}
