package com.example.weatherapp.network

import com.example.weatherapp.WEATHER_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter("APPID", WEATHER_API_KEY).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}