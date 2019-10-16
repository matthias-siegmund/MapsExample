package dev.siegmund.mapsexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.siegmund.map.di.MapModule
import dev.siegmund.map.ui.MapFragment

@Module
abstract class BindingModule {
    @Suppress("unused")
    @ContributesAndroidInjector(modules = [MapModule::class])
    internal abstract fun bindMapFragment(): MapFragment
}