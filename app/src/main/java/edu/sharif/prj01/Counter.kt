package edu.sharif.prj01

class Counter {
    var count: Long = 0

    fun doWork() {
        for (i in 0..99999) {
            this.count = this.count + 1
        }
    }

    fun safeDoWork() {
        synchronized(this) {
            for (i in 0..99999) {
                this.count = this.count + 1
            }
        }

//        for (i in 0..99999){
//            synchronized(this) {
//                this.count = this.count + 1;
//            }
//        }
    }
}
