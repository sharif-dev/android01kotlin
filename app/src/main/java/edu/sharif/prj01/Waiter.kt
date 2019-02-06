package edu.sharif.prj01

import android.util.Log

import java.util.concurrent.CountDownLatch

class Waiter(private val msg: Message, private val latch: CountDownLatch) : Runnable {
    override fun run() {
        val name = Thread.currentThread().name
        synchronized(msg) {
            try {
                Log.i(MainActivity.TAG, "WaitNotifyTest.Waiter]>> time:${System.currentTimeMillis()}")
                msg.wait()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            // Process the message now
            Log.i(MainActivity.TAG, "WaitNotifyTest.Waiter]>> name:$name processed:${msg.msg}")
            latch.countDown()
        }
    }
}
