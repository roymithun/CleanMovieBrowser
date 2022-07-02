package com.tista.cleanmoviebrowser.app.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tista.cleanmoviebrowser.BuildConfig
import com.tista.cleanmoviebrowser.base.di.DateJsonAdapter
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor

private const val CONNECTION_TIMEOUT = 10 // 10 seconds
private const val READ_TIMEOUT = 2 // 2 seconds
private const val WRITE_TIMEOUT = 2 // 2 seconds
private const val BASE_URL = "https://api.themoviedb.org/3/"

val networkModule = module {
    single { AuthenticationInterceptor(BuildConfig.TMDB_API_KEY) }
    single { StethoInterceptor() }
    single { providesHttpClient(get(), get()) }
    single { providesMoshi() }
    single { providesRetrofit(get(), get()) }
}

internal fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

internal fun providesMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(DateJsonAdapter())
        .build()
}

internal fun providesHttpClient(
    authenticationInterceptor: AuthenticationInterceptor,
    stethoInterceptor: StethoInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)

    builder.addInterceptor(stethoInterceptor)
    builder.addInterceptor(authenticationInterceptor)
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(OkHttpProfilerInterceptor())
    }
    return builder.build()
}