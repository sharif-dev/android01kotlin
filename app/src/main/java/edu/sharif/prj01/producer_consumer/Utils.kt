package edu.sharif.prj01.producer_consumer

internal object Utils {
    fun generateProductId(): Long {
        return System.currentTimeMillis()
    }
}
