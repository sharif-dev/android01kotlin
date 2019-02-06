package edu.sharif.prj01.producer_consumer

import android.util.Log
import edu.sharif.prj01.MainActivity

import java.util.LinkedList
import java.util.Queue

class Buffer(private val size: Int) : java.lang.Object() {
    private val list: Queue<Product> = LinkedList<Product>()

    @Throws(InterruptedException::class)
    internal fun produce() {
        while (true) {
            synchronized(this) {
                while (list.size >= size) wait()
                val product = Product.create()
                list.add(product)
                Log.i(MainActivity.TAG, "ProducerConsumerExample ]]>> produce: $product")
                notify()
                Thread.sleep(PRODUCER_DELAY_TIME.toLong())
            }
        }
    }

    @Throws(InterruptedException::class)
    internal fun consume() {
        while (true) {
            synchronized(this) {
                while (list.size == 0) wait()
                val product = list.poll()
                Log.i(MainActivity.TAG, "ProducerConsumerExample ]]>> consume: $product")
                notify()
                Thread.sleep(CONSUMER_DELAY_TIME.toLong())
            }
        }
    }

    companion object {
        const val DEFAULT_BUFFER_SIZE = 3
        private const val PRODUCER_DELAY_TIME = 2 * 1000
        private const val CONSUMER_DELAY_TIME = 2 * 1000
    }
}
