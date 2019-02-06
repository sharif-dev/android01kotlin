package edu.sharif.prj01

class NotThreadSafe {
    var builder = StringBuilder()

    fun add(text: String) {
        this.builder.append(text)
    }

    class MyRunnable(private var instance: NotThreadSafe) : Runnable {
        override fun run() {
            this.instance.add("some text")
        }
    }
}
