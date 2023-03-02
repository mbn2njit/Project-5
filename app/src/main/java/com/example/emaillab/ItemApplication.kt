package com.codepath.articlesearch

import android.app.Application
import com.example.emaillab.ItemDatabase

class ItemApplication : Application() {
    val db by lazy { ItemDatabase.getInstance(this) }
}