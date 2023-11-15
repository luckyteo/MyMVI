package com.luckyteo.mymvi

import android.app.Application
import com.luckyteo.mymvi.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MVIApplication :Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MVIApplication)
            module { appModules }
        }
    }
}