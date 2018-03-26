package sample.ko2ic.mockk

import java.util.*



/**
 * 日付が引数の日より未来日かどうか。
 * 引数の日より未来日ならtrue。
 */
fun Calendar.isFuture(date: Calendar): Boolean {
    return this.after(date)
}


fun jstCalendar2() = Calendar.getInstance().apply {
    // 単体テストがしずらいからインスタンス引数でtimezoneを渡さない
    timeZone = TimeZone.getTimeZone("Asia/Tokyo")
}

fun jstCalendar(implement: DateExtension = DateExtensionImpl()): Calendar {
    val ext = fun DateExtension.(): Calendar = jstCalendar()

//    val ext: DateExtension.() -> Calendar = {
//        jstCalendar()
//    }
    return ext.invoke(implement)
}


interface DateExtension {
    fun DateExtension.jstCalendar(): Calendar
}

class DateExtensionImpl : DateExtension {
    override fun DateExtension.jstCalendar() = Calendar.getInstance().apply {
        // 単体テストがしずらいからインスタンス引数でtimezoneを渡さない
        timeZone = TimeZone.getTimeZone("Asia/Tokyo")
    }
}
