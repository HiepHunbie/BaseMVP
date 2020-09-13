
package com.hiepnt.basemvp.network.interceptor

import android.content.Context
import com.hiepnt.basemvp.utils.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor constructor(private val context: Context): Interceptor {
    companion object {
        const val AUTH_HEADER_KEY = "x-access-token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val pref = AppPreferences(context)
        if (pref.accessToken.isNotBlank()) {
            builder.removeHeader(AUTH_HEADER_KEY)
                .addHeader(AUTH_HEADER_KEY, pref.accessToken)
        }
        return chain.proceed(builder.build())
    }
}