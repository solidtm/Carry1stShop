package com.solid.carry1stshop

import android.app.Application
import com.solid.carry1stshop.domain.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Carry1stShopApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@Carry1stShopApplication)
            modules(appModule)
        }
    }
}