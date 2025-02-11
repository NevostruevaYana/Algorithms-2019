@file:Suppress("UNUSED_PARAMETER")

package lesson2

import java.io.File
import java.lang.Math.sqrt

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    TODO()
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 *
 * Общий комментарий: решение из Википедии для этой задачи принимается,
 * но приветствуется попытка решить её самостоятельно.
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */

/**
 * n = first.length; m = second.length
 * Сложность по времени: O(n)(i-цикл) * O(m)(j-цикл) = O(m*n)
 * Сложность по памяти: O(m*n)(хранение матрицы m*n)
 */

fun longestCommonSubstring(first: String, second: String): String {
    val count = Array(first.length + 1) { Array(second.length + 1) { 0 } }
    var max = 0
    var index = 0
    val length1 = first.length - 1
    val length2 = second.length - 1
    for (i in 0..length1) {
        for (j in 0..length2) {
            if (first[i] == second[j]) {
                count[i + 1][j + 1] = count[i][j] + 1
                if (count[i + 1][j + 1] > max) {
                    max = count[i + 1][j + 1]
                    index = i
                }
            }
        }
    }
    when (max) {
        0 -> return ""
        else -> return first.substring(index - max + 1, index + 1)
    }
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */

/**
 * n = limit
 * Сложность по времени: O(n)(для внешнего i-цикла) * O(n^(0.5))(j-цикл) = O(n^(1.5))
 * Сложность по памяти: O(1)(хранение limit, i, j, count, n)
 */

fun calcPrimesNumber(limit: Int): Int {
    if (limit <= 1) return 0
    if (limit == 2) return 1
    var count = 1
    for (i in 3..limit step 2) {
        if (isPrime(i)) count++
    }
    return count
}

fun isPrime(n: Int): Boolean {
    for (j in 2..sqrt(n.toDouble()).toInt()) {
        if (n % j == 0)
            return false
    }
    return true
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */

/**
 * Сложность по времени: O(n)(цикл перебора слов) * O(n)(i-цикл) * O(n)(j-цикл)*O(n)(цикл в функции finder) = O(n^4)
 * Сложность по памяти: O(n^2)(хранение мфтрицы) + O(n)(хранение конечного списка result)
 *                      + O(1)(хранение константных переменных) = O(n^2)
 */

fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    val matrix = File(inputName).bufferedReader().readLines().map {
        if (!it.matches("([A-ZА-ЯЁ] )*[A-ZА-ЯЁ]".toRegex()))
            throw IllegalArgumentException()
        it.split(" ").map {
            it.single()
        }.toTypedArray()
    }.toTypedArray()
    val result = mutableListOf<String>()

    val h = matrix.size - 1
    val w = matrix[0].size - 1

    for (word in words) {
        for (i in 0..h) {
            if (result.contains(word))
                break
            for (j in 0..w) {
                val point = Point(i, j)
                val check = mutableListOf<Point>()
                if (finder(matrix, word, point, 0, check)) {
                    result.add(word)
                    break
                }
            }
        }
    }
    return result.toSet()
}

data class Point(val x: Int, val y: Int)

fun finder(matrix: Array<Array<Char>>, word: String, point: Point, index: Int, check: MutableList<Point>): Boolean {
    val i = point.x
    val j = point.y
    if (word[index] != matrix[i][j])
        return false
    if (word.length - 1 == index)
        return true
    val points = mutableListOf<Point>()
    check.add(point)
    if (i != 0)
        points.add(Point(i - 1, j))
    if (i != matrix.size - 1)
        points.add(Point(i + 1, j))
    if (j != 0)
        points.add(Point(i, j - 1))
    if (j != matrix[0].size - 1)
        points.add(Point(i, j + 1))
    for (p in points) {
        if (!check.contains(p)) {
            if (finder(matrix, word, p, index + 1, check))
                return true
        }
    }
    return false
}
