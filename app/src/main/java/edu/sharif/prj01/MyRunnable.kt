package edu.sharif.prj01

import android.util.Log

class MyRunnable : Runnable {
    override fun run() {
        Log.i(
            MainActivity.TAG,
            "MyRunnable ]]>>  pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )
    }
}
