package edu.sharif.prj01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import edu.sharif.prj01.producer_consumer.Buffer;
import edu.sharif.prj01.producer_consumer.ConsumerRunnable;
import edu.sharif.prj01.producer_consumer.ProducerRunnable;

import java.util.concurrent.*;

import static edu.sharif.prj01.producer_consumer.Buffer.DEFAULT_BUFFER_SIZE;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "prj1-thread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ThreadSubclass();
//        ThreadRunnable();
//        AnonymousRunnable();
//        LambdaRunnable();
//        RaceCondition();
//        SynchronizedThread();
//        ThreadSafeMethod();
//        ObjectMemberVariablesNotThreadSafe();
//        ThreadLocalExampleMethod();
//        ReentrantExampleMethod();
//        WaitNotifyTest();
//        ScheduledExecutorServiceMethod();
        ProducerConsumerExample();
    }

    void ProducerConsumerExample() {
        Buffer buffer = new Buffer(DEFAULT_BUFFER_SIZE);

        Thread producer = new Thread(new ProducerRunnable(buffer));
        Thread consumer = new Thread(new ConsumerRunnable(buffer));

        producer.start();
        consumer.start();

        try {
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void ThreadSubclass() {
        Log.i(TAG, "ThreadSubclass, Main Thread ]]>>" +
                " pid: " + android.os.Process.myPid() +
                " tid: " + android.os.Process.myTid() +
                " id: " + Thread.currentThread().getId());

        MyThread myThread = new MyThread();
        myThread.start();

        Log.i(TAG, "ThreadSubclass, Main Thread ]]>>" +
                " pid: " + android.os.Process.myPid() +
                " tid: " + android.os.Process.myTid() +
                " id: " + Thread.currentThread().getId());
    }

    void ThreadRunnable() {
        Log.i(TAG, "ThreadRunnable, Main Thread ]]>>" +
                " pid: " + android.os.Process.myPid() +
                " tid: " + android.os.Process.myTid() +
                " id: " + Thread.currentThread().getId());

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        Log.i(TAG, "ThreadRunnable, Main Thread ]]>>" +
                " pid: " + android.os.Process.myPid() +
                " tid: " + android.os.Process.myTid() +
                " id: " + Thread.currentThread().getId());
    }

    void AnonymousRunnable() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                Log.i(MainActivity.TAG, "AnonymousRunnable ]]>> " +
                        " pid: " + android.os.Process.myPid() +
                        " tid: " + android.os.Process.myTid() +
                        " id: " + Thread.currentThread().getId());
            }
        });
        thread.start();

        Log.i(TAG, "AnonymousRunnable, Main Thread ]]>>" +
                " pid: " + android.os.Process.myPid() +
                " tid: " + android.os.Process.myTid() +
                " id: " + Thread.currentThread().getId());

    }

    void LambdaRunnable() {
        Runnable lambda = () ->
                Log.i(MainActivity.TAG, "LambdaRunnable ]]>> " +
                        " pid: " + android.os.Process.myPid() +
                        " tid: " + android.os.Process.myTid() +
                        " id: " + Thread.currentThread().getId());

        Thread thread = new Thread(lambda);
        //Thread thread = new Thread(() -> Log.i(MainActivity.TAG, ""));
        thread.start();
        Log.i(TAG, "LambdaRunnable, Main Thread ]]>>" +
                " pid: " + android.os.Process.myPid() +
                " tid: " + android.os.Process.myTid() +
                " id: " + Thread.currentThread().getId());
    }

    void RaceCondition() {
        Counter c = new Counter();

        Thread thread1 = new Thread(() -> c.doWork());
        Thread thread2 = new Thread(() -> c.doWork());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(MainActivity.TAG, "RaceCondition ]]>> " +
                " count: " + c.count);
    }

    void SynchronizedThread() {
        Counter c = new Counter();

        Thread thread1 = new Thread(() -> c.safeDoWork());
        Thread thread2 = new Thread(() -> c.safeDoWork());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(MainActivity.TAG, "SynchronizedThread ]]>> " +
                " count: " + c.count);
    }

    void ThreadSafeMethod() {
        ThreadSafe threadSafe = new ThreadSafe();
        threadSafe.LocalObjectReferences();
    }

    void ObjectMemberVariablesNotThreadSafe() {
        NotThreadSafe sharedInstance = new NotThreadSafe();

        Thread t1 = new Thread(new NotThreadSafe.MyRunnable(sharedInstance));
        Thread t2 = new Thread(new NotThreadSafe.MyRunnable(sharedInstance));
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(MainActivity.TAG, "ObjectMemberVariablesNotThreadSafe]]>>" +
                sharedInstance.builder.toString());
    }

    void ThreadLocalExampleMethod() {
        ThreadLocalExample sharedInstance = new ThreadLocalExample();

        Thread t1 = new Thread(() -> sharedInstance.doWork());
        Thread t2 = new Thread(() -> sharedInstance.doWork());
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(MainActivity.TAG, "ThreadLocalExampleMethod]]>>" +
                sharedInstance.getValue());
    }

    void ReentrantExampleMethod() {
        Reentrant sharedInstance = new Reentrant();
        Log.i(MainActivity.TAG, "ReentrantExampleMethod]>> start");

        Thread t1 = new Thread(() -> sharedInstance.outer());
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(MainActivity.TAG, "ReentrantExampleMethod]>> end");
    }

    void WaitNotifyTest() {
        // intialising count down latch by 2,
        // as it will wait for 2 threads to finish execution
        final CountDownLatch latch = new CountDownLatch(2);

        Message msg = new Message("process it");

        Waiter waiter = new Waiter(msg, latch);
        new Thread(waiter, "waiter1").start();

        Waiter waiter1 = new Waiter(msg, latch);
        new Thread(waiter1, "waiter2").start();

        Notifier notifier = new Notifier(msg, latch);
        new Thread(notifier, "notifier1").start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(MainActivity.TAG, "WaitNotifyTest]>> end");
    }

    void ScheduledExecutorServiceMethod() {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture =
                scheduledExecutorService.schedule(
                        new Callable() {
                            public Object call() throws Exception {
                                Log.i(MainActivity.TAG, "ScheduledExecutorService]>> Executed");
                                return "Called!";
                            }
                        },
                        5,
                        TimeUnit.SECONDS);
    }
}
