package co.thrivemobile.bpt.database

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.ZoneOffset

class BptTypeConvertersTest {

    @Nested
    @DisplayName("Given a valid offset date")
    inner class ValidOffsetDate {

        private val exptectedConvertedString = "2019-05-21T00:00:00+02:00"
        private val expectedOffsetDate = OffsetDateTime.of(2019,5,21, 0,0,0,0, ZoneOffset.of("+02:00"))

        @Test
        @DisplayName("When inserting into database it is converted to correct string")
        fun testOffsetDateConversion() {
            val convertedString = BptTypeConverters.fromOffsetDateTime(expectedOffsetDate)

            assertNotNull(convertedString)
            assertTrue(convertedString == exptectedConvertedString)
        }

        @Test
        @DisplayName("When retrieving from the database it is converted to correct OffSetDate")
        fun testStringToDateConversion() {
            val convertedOffsetDate = BptTypeConverters.toOffsetDateTime(exptectedConvertedString)

            assertNotNull(convertedOffsetDate)
            assertTrue(convertedOffsetDate == expectedOffsetDate)
        }
    }

    @Nested
    @DisplayName("Given a null offset date")
    inner class NullOffsetDate {

        @Test
        @DisplayName("When inserting into database it returns null")
        fun testNullDateConversion() {
            val convertedString = BptTypeConverters.fromOffsetDateTime(null)
            assertNull(convertedString)
        }

        @Test
        @DisplayName("When retrieving from database it returns null")
        fun testNullDateRetrieval() {
            val convertedOffsetDate = BptTypeConverters.toOffsetDateTime(null)
            assertNull(convertedOffsetDate)
        }
    }

    @Nested
    @DisplayName("Given a valid time")
    inner class ValidOffsetTime {

        private val expectedConvertedString = "16:30:00+02:00"
        private val expectedConvertedOffsetTime = OffsetTime.of(16, 30, 0, 0, ZoneOffset.of("+02:00"))

        @Test
        @DisplayName("When inserting into database it is properly converted to string")
        fun testOffsetTimeConversion() {
            val convertedString = BptTypeConverters.fromOffsetTime(expectedConvertedOffsetTime)
            assertNotNull(convertedString)
            assertTrue(convertedString == expectedConvertedString)
        }

        @Test
        @DisplayName("When retrieving from database it is properly converted to offset time")
        fun testStringConversion() {
            val offsetTime = BptTypeConverters.toOffsetTime(expectedConvertedString)
            assertNotNull(offsetTime)
            assertTrue(offsetTime == expectedConvertedOffsetTime)
        }
    }

    @Nested
    @DisplayName("Given a null offset time")
    inner class NullOffsetTime {

        @Test
        @DisplayName("When inserting into database it returns null")
        fun testNullTimeConversion() {
            val convertedString = BptTypeConverters.fromOffsetTime(null)
            assertNull(convertedString)
        }

        @Test
        @DisplayName("When retrieving from database it returns null")
        fun testNullTimeRetrieval() {
            val convertedOffsetDate = BptTypeConverters.toOffsetTime(null)
            assertNull(convertedOffsetDate)
        }
    }
}
