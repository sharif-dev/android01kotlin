package edu.sharif.prj01

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import edu.sharif.prj01.producer_consumer.Buffer
import edu.sharif.prj01.producer_consumer.ConsumerRunnable
import edu.sharif.prj01.producer_consumer.ProducerRunnable

import java.util.concurrent.*

import edu.sharif.prj01.producer_consumer.Buffer.Companion.DEFAULT_BUFFER_SIZE
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        ThreadSubclass()
//        ThreadRunnable()
//        AnonymousRunnable()
//        LambdaRunnable()
//        RaceCondition()
//        SynchronizedThread()
//        ThreadSafeMethod()
//        ObjectMemberVariablesNotThreadSafe()
//        ThreadLocalExampleMethod()
//        ReentrantExampleMethod()
//        WaitNotifyTest()
//        ScheduledExecutorServiceMethod()
//        ProducerConsumerExample()
    }

    internal fun ProducerConsumerExample() {
        val buffer = Buffer(DEFAULT_BUFFER_SIZE)

        val producer = Thread(ProducerRunnable(buffer))
        val consumer = Thread(ConsumerRunnable(buffer))

        producer.start()
        consumer.start()

        try {
            producer.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        try {
            consumer.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    internal fun ThreadSubclass() {
        Log.i(
            TAG,
            "ThreadSubclass, Main Thread ]]>> pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )

        val myThread = MyThread()
        myThread.start()

        Log.i(
            TAG,
            "ThreadSubclass, Main Thread ]]>> pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )
    }

    internal fun ThreadRunnable() {
        Log.i(
            TAG,
            "ThreadRunnable, Main Thread ]]>> pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )

        val myRunnable = MyRunnable()
        val thread = Thread(myRunnable)
        thread.start()

        Log.i(
            TAG,
            "ThreadRunnable, Main Thread ]]>> pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )
    }

    internal fun AnonymousRunnable() {
        val thread = Thread(Runnable {
            Log.i(
                MainActivity.TAG,
                "AnonymousRunnable ]]>>  pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
            )
        })
        thread.start()

        Log.i(
            TAG,
            "AnonymousRunnable, Main Thread ]]>> pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )

    }

    internal fun LambdaRunnable() {
        thread(start = true) {
            Log.i(
                MainActivity.TAG,
                "LambdaRunnable ]]>>  pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
            )
        }

        Log.i(
            TAG,
            "LambdaRunnable, Main Thread ]]>> pid: ${android.os.Process.myPid()} tid: ${android.os.Process.myTid()} id: ${Thread.currentThread().id}"
        )
    }

    internal fun RaceCondition() {
        val c = Counter()

        val thread1 = Thread { c.doWork() }
        val thread2 = Thread { c.doWork() }

        thread1.start()
        thread2.start()

        try {
            thread1.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        try {
            thread2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.i(MainActivity.TAG, "RaceCondition ]]>>  count: ${c.count}")
    }

    internal fun SynchronizedThread() {
        val c = Counter()

        val thread1 = Thread { c.safeDoWork() }
        val thread2 = Thread { c.safeDoWork() }

        thread1.start()
        thread2.start()

        try {
            thread1.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        try {
            thread2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.i(MainActivity.TAG, "SynchronizedThread ]]>>  count: ${c.count}")
    }

    internal fun ThreadSafeMethod() {
        val threadSafe = ThreadSafe()
        threadSafe.LocalObjectReferences()
    }

    internal fun ObjectMemberVariablesNotThreadSafe() {
        val sharedInstance = NotThreadSafe()

        val t1 = Thread(NotThreadSafe.MyRunnable(sharedInstance))
        val t2 = Thread(NotThreadSafe.MyRunnable(sharedInstance))
        t1.start()
        t2.start()
        try {
            t1.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        try {
            t2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.i(MainActivity.TAG, "ObjectMemberVariablesNotThreadSafe]]>>${sharedInstance.builder}")
    }

    internal fun ThreadLocalExampleMethod() {
        val sharedInstance = ThreadLocalExample()

        val t1 = Thread { sharedInstance.doWork() }
        val t2 = Thread { sharedInstance.doWork() }
        t1.start()
        t2.start()
        try {
            t1.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        try {
            t2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.i(MainActivity.TAG, "ThreadLocalExampleMethod]]>>${sharedInstance.value}")
    }

    internal fun ReentrantExampleMethod() {
        val sharedInstance = Reentrant()
        Log.i(MainActivity.TAG, "ReentrantExampleMethod]>> start")

        val t1 = Thread { sharedInstance.outer() }
        t1.start()
        try {
            t1.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.i(MainActivity.TAG, "ReentrantExampleMethod]>> end")
    }

    internal fun WaitNotifyTest() {
        // Initialising count down latch by 2, as it will wait for 2 threads to finish execution
        val latch = CountDownLatch(2)

        val msg = Message("process it")

        val waiter = Waiter(msg, latch)
        Thread(waiter, "waiter1").start()

        val waiter1 = Waiter(msg, latch)
        Thread(waiter1, "waiter2").start()

        val notifier = Notifier(msg, latch)
        Thread(notifier, "notifier1").start()

        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.i(MainActivity.TAG, "WaitNotifyTest]>> end")
    }

    internal fun ScheduledExecutorServiceMethod() {
        val scheduledExecutorService = Executors.newScheduledThreadPool(5)

        val scheduledFuture = scheduledExecutorService.schedule(
            {
                Log.i(MainActivity.TAG, "ScheduledExecutorService]>> Executed")
            },
            5,
            TimeUnit.SECONDS
        )
    }

    companion object {
        const val TAG = "prj1-thread"
    }
}
