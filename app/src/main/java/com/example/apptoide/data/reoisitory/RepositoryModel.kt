package com.example.apptoide.data.reoisitory

import com.example.apptoide.data.structure.TabsStructure
import com.example.apptoide.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryModel {

    suspend fun getAllData(): Flow<Resource<TabsStructure>>
}
