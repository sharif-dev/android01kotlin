package edu.sharif.prj01

import android.util.Log

class ThreadSafe {
    fun LocalVariables() {
        var threadSafeInt: Long = 0
        threadSafeInt++
    }
    /*
        If an object created locally never escapes the method it was created in, it is thread safe.
        In fact you can also pass it on to other methods and objects as long as none of these methods
        or objects make the passed object available to other threads.
    */

    /*
        The LocalObject instance in this example is not returned from the method, nor is it passed
        to any other objects that are accessible from outside the someMethod() method.
        Each thread executing the someMethod() method will create its own LocalObject instance and
        assign it to the localObject reference. Therefore the use of the LocalObject here is thread safe.

        In fact, the whole method someMethod() is thread safe. Even if the LocalObject instance is
        passed as parameter to other methods in the same class, or in other classes,
        the use of it is thread safe.

        The only exception is of course, if one of the methods called with the LocalObject as
        parameter, stores the LocalObject instance in a way that allows access to it from other threads.
    */
    fun LocalObjectReferences() {
        val localObject = LocalObject()
        method2(localObject)
        Log.i(MainActivity.TAG, "value:" + localObject.value)
    }

    fun method2(value: LocalObject) {
        value.value = 200
    }


    companion object {
        class LocalObject {
            var value: Int = 0
        }
    }
}
