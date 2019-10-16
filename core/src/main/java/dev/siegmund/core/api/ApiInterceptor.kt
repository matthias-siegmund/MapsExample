package dev.siegmund.core.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request()
            .url
            .newBuilder()
            .build()

        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .url(url)
                .build()
        )
    }
}