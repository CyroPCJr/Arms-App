package com.example.armsapp

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import com.example.armsapp.di.ArmsAppContainer
import com.example.armsapp.di.DependencyContainer

class ArmsApplication : Application(), SingletonImageLoader.Factory {

    lateinit var container: DependencyContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = ArmsAppContainer.getInstance(this)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        val imageLoader = ImageLoader.Builder(context)
            .memoryCachePolicy(policy = CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context = context, percent = 0.25)
                    .build()
            }
            .diskCachePolicy(policy = CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve(relative = "image_cache"))
                    .maxSizePercent(percent = 0.02)
                    .build()
            }
            .logger(logger = DebugLogger())
            .build()
        return imageLoader
    }

}