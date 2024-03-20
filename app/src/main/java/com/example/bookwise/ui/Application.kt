package com.example.bookwise.ui

import android.app.Application
import com.example.bookwise.data.AppContainer
import com.example.bookwise.data.DefaultAppContainer

class Application : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}