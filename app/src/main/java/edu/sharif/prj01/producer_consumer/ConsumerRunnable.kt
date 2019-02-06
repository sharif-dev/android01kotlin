package edu.sharif.prj01.producer_consumer

class ConsumerRunnable(private val buffer: Buffer) : Runnable {
    override fun run() {
        try {
            buffer.consume()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
