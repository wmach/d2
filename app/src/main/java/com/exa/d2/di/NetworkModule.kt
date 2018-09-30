package com.exa.d2.di

import android.util.Log
import com.exa.d2.BuildConfig
import com.exa.d2.data.api.TrainApi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.exa.d2.data.model.ApplicationJsonAdapterFactory
import com.exa.d2.presentation.App
import com.exa.d2.util.Config.CONNECT_TIMEOUT
import com.exa.d2.util.Config.READ_TIMEOUT
import com.exa.d2.util.Config.WRITE_TIMEOUT
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val TAG = "NetworkModule"
    }

    @Singleton @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(ApplicationJsonAdapterFactory.INSTANCE)
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
    }

    @Singleton @Provides @Named("train")
    fun provideHttpClient(app: App, moshi: Moshi): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()
    }

    @Singleton @Provides
    fun provideTrainApi(@Named("train") httpClient: OkHttpClient, moshi: Moshi): TrainApi {
        val retrofit = Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(TrainApi::class.java)
    }
}