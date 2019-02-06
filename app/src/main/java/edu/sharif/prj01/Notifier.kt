package edu.sharif.prj01

import android.util.Log

import java.util.concurrent.CountDownLatch

class Notifier(private val msg: Message, private val latch: CountDownLatch) : Runnable {
    override fun run() {
        val name = Thread.currentThread().name
        Log.i(MainActivity.TAG, "WaitNotifyTest.Notifier]>> started")
        try {
            Thread.sleep(1000)
            synchronized(msg) {
                msg.msg = "$name Notifier work done"
                msg.notify()
                // msg.notifyAll();
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        this.latch.countDown()
    }
}
