package edu.sharif.prj01

/*
    The full volatile visibility guarantee means,
    that when a value is written to days, then all
    variables visible to the thread are also written
    to main memory. That means, that when a value is
    written to days, the values of years and months
    are also written to main memory.

    Notice the totalDays() method starts by reading
    the value of days into the total variable. When
    reading the value of days, the values of months
    and years are also read into main memory. Therefore
    you are guaranteed to see the latest values of days,
    months and years with the above read sequence.
*/
class VolatileSharedObject {
    private var years: Int = 0
    private var months: Int = 0
    @Volatile
    private var days: Int = 0

    fun totalDays(): Int {
        var total = this.days
        total += months * 30
        total += years * 365
        return total
    }

    fun update(years: Int, months: Int, days: Int) {
        this.years = years
        this.months = months
        this.days = days
    }

    /*
        The values of months and years are still written
        to main memory when the days variable is modified,
        but this time it happens before the new values
        have been written to months and years. The new
        values are thus not properly made visible to other
        threads
    */
    fun update2(years: Int, months: Int, days: Int) {
        this.days = days // note
        this.months = months
        this.years = years
    }
}
