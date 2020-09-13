
package com.hiepnt.basemvp.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

class HttpErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code()) {
//            HttpURLConnection.HTTP_UNAUTHORIZED -> RxBus.send(TokenExpiredEvent())
        }
        return response
    }
}