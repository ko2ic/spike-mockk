package sample.ko2ic.mockk

import java.util.*

class Domain {


    fun day() :Int{
        val calendar = jstCalendar2()
        return calendar.get(Calendar.DAY_OF_MONTH)
    }
}