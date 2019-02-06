package edu.sharif.prj01

class ThreadLocalExample {
    private val threadLocal = ThreadLocal<Int>()

    val value: Int?
        get() = threadLocal.get()

    internal fun doWork() {
        threadLocal.set((Math.random() * 100.0).toInt())
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
        }
    }
}
