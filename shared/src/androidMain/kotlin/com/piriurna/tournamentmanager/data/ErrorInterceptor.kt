package com.piriurna.tournamentmanager.data

import com.piriurna.tournamentmanager.domain.GlobalNavigator
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val response = chain.proceed(builder.build())

        if (response.code == 401) {
            GlobalNavigator.logout()
        }

        return response
    }
}