@file:Suppress("UNUSED_PARAMETER")

package lesson6

import java.io.File
import java.lang.Math.max
import java.lang.Math.min

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */

/**
 * n - длина 1 строки, m - длина 2 строки
 * Сложность по времени: O(n)(i-цикл) * O(m)(j-цикл) = O(m*n)
 * Сложность по памяти: O(m*n)(хранение матрицы m*n)
 */

fun longestCommonSubSequence(first: String, second: String): String {
    val table = Array(first.length + 1) { Array(second.length + 1) { 0 } }
    for (i in first.length - 1 downTo 0) {
        for (j in second.length - 1 downTo 0) {
            if (first[i] == second[j])
                table[i][j] = table[i + 1][j + 1] + 1
            else
                table[i][j] = max(table[i + 1][j], table[i][j + 1])
        }
    }

    var res = ""
    var i = 0
    var j = 0

    while (i < first.length && j < second.length) {
        when {
            first[i] == second[j] -> {
                res += first[i]
                i++
                j++
            }
            table[i + 1][j] >= table[i][j + 1] -> i++
            else -> j++
        }
    }

    return res
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */

/**
 * n - количество стоблцов, m - количество строк
 * Сложность по времени: O(n)(i-цикл) * O(m)(j-цикл) = O(m*n)
 * Сложность по памяти: O(m*n)(хранение матрицы m*n)
 */

fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty())
        return list

    val res = mutableListOf<Int>()
    var maxLength = 0
    var lastPosition = 0
    val size = list.size
    val index = Array(size) { -1 }
    val length = Array(size) { 0 }

    for (i in 0 until size) {
        for (j in 0 until i) {
            if (length[i] < length[j] + 1 && list[i] > list[j]) {
                length[i] = length[j] + 1
                index[i] = j
                if (maxLength < length[i]) {
                    maxLength = length[i]
                    lastPosition = i
                }
            }
        }
    }

    for (i in 0..maxLength) {
        res.add(list[lastPosition])
        lastPosition = index[lastPosition]
    }

    return res.reversed()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */

/**
 * n - количество стоблцов, m - количество строк
 * Сложность по времени: O(n)(i-цикл) * O(m)(j-цикл) = O(m*n)
 * Сложность по памяти: O(m*n)(хранение матрицы m*n)
 */

fun shortestPathOnField(inputName: String): Int {
    val field = File(inputName).bufferedReader()
        .readLines().map { it.split(" ").map { it.toInt() }.toTypedArray() }.toTypedArray()
    if (field.isEmpty())
        return 0
    val width = field[0].size
    val height = field.size
    for (i in 1 until width)
        field[0][i] = field[0][i] + field[0][i - 1]
    for (i in 1 until height)
        field[i][0] = field[i][0] + field[i - 1][0]
    for (i in 1 until height)
        for (j in 1 until width) {
            field[i][j] = field[i][j] + min(field[i - 1][j],
                min(field[i][j - 1], field[i - 1][j - 1]))
        }
    return field[height - 1][width - 1]
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5