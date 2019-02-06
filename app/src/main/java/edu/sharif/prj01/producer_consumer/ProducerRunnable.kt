package edu.sharif.prj01.producer_consumer

class ProducerRunnable(private val buffer: Buffer) : Runnable {
    override fun run() {
        try {
            buffer.produce()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
