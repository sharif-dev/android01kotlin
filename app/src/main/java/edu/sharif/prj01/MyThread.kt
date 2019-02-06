package edu.sharif.prj01

import android.util.Log

class MyThread : Thread() {
    override fun run() {
        // Process.myTid() is the linux thread ID
        // Thread.getId() is a simple sequential long number
        Log.i(
            MainActivity.TAG,
            "MyThread ]]>>  pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )
    }
}
