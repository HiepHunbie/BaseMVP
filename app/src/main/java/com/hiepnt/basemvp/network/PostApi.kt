package com.hiepnt.basemvp.network

import com.hiepnt.basemvp.model.DataListDetail
import io.reactivex.Observable
import retrofit2.http.*
import java.util.*


/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {

    @Headers("Content-Type: application/json")
    @GET("category")
    fun getListData(): Observable<DataListDetail>


}