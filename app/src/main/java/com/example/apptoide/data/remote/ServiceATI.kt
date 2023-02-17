package com.example.apptoide.data.remote

import com.example.apptoide.data.remote.ATIReferences.ATI_END_POINT
import com.example.apptoide.data.structure.TabsStructure
import retrofit2.Response
import retrofit2.http.GET

interface ServiceATI {

    @GET(ATI_END_POINT)
    suspend fun getAllData(): Response<TabsStructure>
}
