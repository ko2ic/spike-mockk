package sample.ko2ic.mockk

import io.mockk.every
import io.mockk.staticMockk
import io.mockk.use
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import org.powermock.api.mockito.PowerMockito

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.powermock.api.mockito.internal.PowerMockitoCore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.util.*

@RunWith(PowerMockRunner::class)
@PrepareForTest(Calendar::class)
class ExampleUnitTest {
    @Test
    fun day() {
        val target = Domain()

        val now = Calendar.getInstance().apply {
            clear()
            set(2000,Calendar.JANUARY,1)
        }

        staticMockk("sample.ko2ic.mockk.DateExtensionKt").use {
            every {
                jstCalendar2()
            } returns now

            assertEquals(1, target.day())

            verify {
                jstCalendar2()
            }
        }
    }

    @Test
    fun day_mockito(){
            val now = Calendar.getInstance().apply {
                clear()
                set(2000,Calendar.JANUARY,1)
            }

            // Calendar.getInstanceだけmock化する
            PowerMockito.spy(Calendar::class.java)
            PowerMockito.mockStatic(Calendar::class.java)
            PowerMockito.`when`(Calendar.getInstance()).thenReturn(now)

            val target = Domain()
            val actual = target.day()

            PowerMockito.verifyStatic(Calendar::class.java);
            Calendar.getInstance()

            Assert.assertEquals(1, actual)

            // reset
            PowerMockito.doCallRealMethod().`when`(Calendar::class.java)
    }
}
