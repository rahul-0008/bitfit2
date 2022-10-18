package com.example.bitfit2

import android.app.Application
import androidx.fragment.app.Fragment

class ArticleApplication2 (): Application()  {
    val db by lazy { AppDatabase2.getInstance(this) }
}