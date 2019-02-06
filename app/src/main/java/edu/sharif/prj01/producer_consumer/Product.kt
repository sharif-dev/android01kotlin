package edu.sharif.prj01.producer_consumer

class Product private constructor(val id: Long) {
    override fun toString() = "Product{id=$id}"

    companion object {
        internal fun create(): Product {
            return Product(Utils.generateProductId())
        }
    }
}
