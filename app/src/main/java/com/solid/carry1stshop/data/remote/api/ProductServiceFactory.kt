package com.solid.carry1stshop.data.remote.api

import com.solid.carry1stshop.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * A factory class for creating instances of the ProductService API.
 */
object ProductServiceFactory {

    /**
     * Creates and returns an instance of the ProductService.
     *
     * @param isDebug A boolean flag indicating whether the application is in debug mode.
     * @return An instance of ProductService.
     */
    fun createApiService(isDebug: Boolean): ProductService {
        val okHttpClient: OkHttpClient = makeOkHttpClient(
            makeLoggingInterceptor((isDebug))
        )
        return makeApiService(okHttpClient)
    }


    /**
     * Creates and returns an instance of the ProductService using the provided OkHttpClient.
     *
     * @param okHttpClient The OkHttpClient to be used for creating the Retrofit instance.
     * @return An instance of ProductService.
     */
    private fun makeApiService(okHttpClient: OkHttpClient): ProductService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(ProductService::class.java)
    }

    /**
     * Creates and returns an OkHttpClient configured with the provided HttpLoggingInterceptor.
     *
     * @param httpLoggingInterceptor The HttpLoggingInterceptor to be added to the OkHttpClient.
     * @return An instance of OkHttpClient.
     */
    fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Creates and returns an HttpLoggingInterceptor configured based on the debug mode.
     *
     * @param isDebug A boolean flag indicating whether the application is in debug mode.
     * @return An instance of HttpLoggingInterceptor.
     */
    fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}