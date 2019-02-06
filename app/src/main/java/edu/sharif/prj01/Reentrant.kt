package edu.sharif.prj01

import android.util.Log

class Reentrant {
    @Synchronized
    fun outer() {
        Log.i(MainActivity.TAG, "outer")
        inner()
    }

    @Synchronized
    fun inner() {
        Log.i(MainActivity.TAG, "inner")
        // Do something
    }
}
