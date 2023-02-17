package com.example.apptoide.domain

import android.util.Log
import com.example.apptoide.data.remote.ServiceATI
import com.example.apptoide.data.reoisitory.RepositoryModel
import com.example.apptoide.data.structure.TabsStructure
import com.example.apptoide.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: ServiceATI) : RepositoryModel {
    override suspend fun getAllData(): Flow<Resource<TabsStructure>> = flow {
        emit(Resource.Loading())
        try {
            Log.i("DebugNetworkRepo", "Pass getAllNews ${remoteDataSource.getAllData()}")
            val response = remoteDataSource.getAllData()

            if (response.isSuccessful)
                response.body()?.let {
                    emit(Resource.Success(it))
                    Log.i("DebugNetworkRepoLet", response.toString())
                }
            else emit(Resource.Error(response.code().toString()))
        } catch (e: HttpException) {
            emit(Resource.Error("Could not load data"))
            Log.i("DebugNetworkRepo", "load data error")
        } catch (e: IOException) {
            emit(Resource.Error("Check internet"))
            Log.i("DebugNetworkRepo", "check internet error")
        }
    }
}
