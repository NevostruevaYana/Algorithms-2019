package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class AlgorithmsTestsKotlin : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> longestCommonSubstring(first, second) }
        assertEquals("серватор", longestCommonSubstring("консерватор", "обсерватория"))
        assertEquals("apples", longestCommonSubstring("apples", "applesauce"))
        assertEquals("apples pie", longestCommonSubstring("apples pie", "apples pies"))
        assertEquals("il", longestCommonSubstring("april", "child"))
        assertEquals("app", longestCommonSubstring("apples", "lesapp"))
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumber() {
        calcPrimesNumber { calcPrimesNumber(it) }
        assertEquals(0, calcPrimesNumber(-5))
        assertEquals(0, calcPrimesNumber(1))
        assertEquals(1, calcPrimesNumber(2))
        assertEquals(2, calcPrimesNumber(3))
        assertEquals(4, calcPrimesNumber(7))
        assertEquals(40, calcPrimesNumber(174))
        assertEquals(430, calcPrimesNumber(3000))
        assertEquals(1229, calcPrimesNumber(10000))
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> baldaSearcher(inputName, words) }
        assertEquals(setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ"), baldaSearcher(
            "test/lesson2/baldaSearcherTest1", setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ", "РАК")))
        assertEquals(setOf("SOFA", "BILD", "COUCH"), baldaSearcher(
            "test/lesson2/baldaSearcherTest2", setOf("SOFA", "BIRD", "BILD", "COUCH", "SCHNH")))
        assertEquals(setOf("SOFA", "SESSEL", "BILD", "COUCH", "LAMPE"), baldaSearcher(
            "test/lesson2/baldaSearcherTest2", setOf("SOFA", "SESSEL", "BILD", "COUCH", "LAMPE")))
        assertEquals(setOf(), baldaSearcher(
            "test/lesson2/baldaSearcherTest1", setOf("ТРАВВ", "КРАА", "РАК")))
    }
}