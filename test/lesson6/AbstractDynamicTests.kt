package lesson6

import kotlin.test.assertEquals

abstract class AbstractDynamicTests {
    fun longestCommonSubSequence(longestCommonSubSequence: (String, String) -> String) {
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("1", longestCommonSubSequence("1", "1"))
        assertEquals("13", longestCommonSubSequence("123", "13"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
        val expectedLength = "e kerwelkkd r".length
        assertEquals(
            expectedLength, longestCommonSubSequence(
                "oiweijgw kejrhwejelkrw kjhdkfjs hrk",
                "perhkhk lerkerorwetp lkjklvvd durltr"
            ).length, "Answer must have length of $expectedLength, e.g. 'e kerwelkkd r' or 'erhlkrw kjk r'"
        )
        val expectedLength2 = """ дд саы чтых,
евшнео ваа се сви дн.
        """.trimIndent().length
        assertEquals(
            expectedLength2, longestCommonSubSequence(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength2"
        )
        assertEquals("рдлладжоидыждлопрамыпер", longestCommonSubSequence("чсрмдяылларыдфжосридоыждлогкпраомрыжывдоеплаер",
            "лорпукджэпклдоадвлдпенгшколадвбтипаоалдвжывоамивджщыждалпоэвыдлапртамьсбыщшкгпримтьщукшегрп" ))
        assertEquals("рплываив", longestCommonSubSequence("1варплоываптивлапт", "ырвпалыоваримывр"))
        assertEquals("254656437928", longestCommonSubSequence("374538945192875465643178928345", "253465678435729128"))
    }

    fun longestIncreasingSubSequence(longestIncreasingSubSequence: (List<Int>) -> List<Int>) {
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2), longestIncreasingSubSequence(listOf(2, 1)))
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
        assertEquals(
            listOf(23, 34, 56, 87, 91, 98, 140, 349), longestIncreasingSubSequence(
                listOf(
                    23, 76, 34, 93, 123, 21, 56, 87, 91, 12, 45, 98, 140, 12, 5, 38, 349, 65, 94,
                    45, 76, 15, 99, 100, 88, 84, 35, 88
                )
            )
        )
        assertEquals(
            listOf(12, 17, 26, 30, 44, 54, 59, 62, 90, 123, 150, 180, 267), longestIncreasingSubSequence(
                listOf(
                    71, 18, 123, 65, 34, 35, 78, 12, 35, 69, 83, 45, 67, 99, 133, 64, 9, 0, 56, 45, 79, 98, 145, 218, 17, 26, 30, 44, 71, 54, 156, 222,
                    48, 59, 26, 11, 49, 62, 61, 40, 90, 36, 190, 236, 57, 38, 79, 123, 150, 180, 267
                )
            )
        )
        assertEquals(
            listOf(1, 2, 5, 6, 7), longestIncreasingSubSequence(
                listOf(
                    1, 2, 5, 6, 7, 3, 4, 5
                )
            )
        )
        assertEquals(
            listOf(1, 6, 9, 23, 45, 78, 90, 94), longestIncreasingSubSequence(
                listOf(
                    1, 6, 9, 3, 56, 23, 45, 78, 90, 7, 5, 94
                )
            )
        )
    }

    fun shortestPathOnField(shortestPathOnField: (String) -> Int) {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
        assertEquals(43, shortestPathOnField("input/field_in3.txt"))
        assertEquals(28, shortestPathOnField("input/field_in4.txt"))
        assertEquals(222, shortestPathOnField("input/field_in5.txt"))
        assertEquals(15, shortestPathOnField("input/field_in6.txt"))
        assertEquals(120, shortestPathOnField("input/field_in7.txt"))
        assertEquals(117, shortestPathOnField("input/field_in8.txt"))
        assertEquals(303, shortestPathOnField("input/field_in9.txt"))
        assertEquals(382, shortestPathOnField("input/field_in10.txt"))
    }

}