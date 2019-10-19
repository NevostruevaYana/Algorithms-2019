@file:Suppress("UNUSED_PARAMETER")

package lesson1

import java.io.File
import java.lang.IllegalArgumentException

/**
 * Сортировка времён
 *
 * Простая
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
 * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
 *
 * Пример:
 *
 * 01:15:19 PM
 * 07:26:57 AM
 * 10:00:03 AM
 * 07:56:14 PM
 * 01:15:19 PM
 * 12:40:31 AM
 *
 * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
 * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
 *
 * 12:40:31 AM
 * 07:26:57 AM
 * 10:00:03 AM
 * 01:15:19 PM
 * 01:15:19 PM
 * 07:56:14 PM
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortTimes(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сортировка адресов
 *
 * Средняя
 *
 * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
 * где они прописаны. Пример:
 *
 * Петров Иван - Железнодорожная 3
 * Сидоров Петр - Садовая 5
 * Иванов Алексей - Железнодорожная 7
 * Сидорова Мария - Садовая 5
 * Иванов Михаил - Железнодорожная 7
 *
 * Людей в городе может быть до миллиона.
 *
 * Вывести записи в выходной файл outputName,
 * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
 * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
 *
 * Железнодорожная 3 - Петров Иван
 * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
 * Садовая 5 - Сидоров Петр, Сидорова Мария
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */

/**
 * m - количество адресов
 * n - среднее количетво человек, проживающих по одному адресу
 * Сложность по времени: O(n*m*logn*logm) (сложность sortedWith() - O(n*logn))
 * Сложность по памяти: O(n*m)
 */

data class Person(val name: String, val lastName: String)

data class Address(val street: String, val num: Int)

fun sortAddresses(inputName: String, outputName: String) {
    val output = HashMap<Address, MutableList<Person>>()
    File(inputName).bufferedReader().readLines().forEach {
        if (!it.matches("\\S* \\S* - \\S* [1-9][0-9]*".toRegex()))
            throw IllegalArgumentException()
        val part = it.split("\\s-?\\s?".toRegex())
        val address = Address(part[2], part[3].toInt())
        val person = Person(part[0], part[1])
        if (!output.containsKey(address))
            output[address] = mutableListOf<Person>()
        output[address]!!.add(person)
    }
    val bw = File(outputName).bufferedWriter()
    output.keys.sortedWith(compareBy({ it.street }, { it.num })).forEach {
        bw.write(it.street + " " + it.num + " - ")
        val sortPersons = output[it]!!.sortedWith(compareBy({ it.name }, { it.lastName }))
        bw.write(sortPersons.joinToString { "${it.name} ${it.lastName}" })
        bw.newLine()
    }
    bw.close()
}

/**
 * Сортировка температур
 *
 * Средняя
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
 * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
 * Например:
 *
 * 24.7
 * -12.6
 * 121.3
 * -98.4
 * 99.5
 * -12.6
 * 11.0
 *
 * Количество строк в файле может достигать ста миллионов.
 * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
 * Повторяющиеся строки сохранить. Например:
 *
 * -98.4
 * -12.6
 * -12.6
 * 11.0
 * 24.7
 * 99.5
 * 121.3
 */

/**
 * n - количество температур
 * Сложность по времени: O(n) (сложность countingSort())
 * Сложность по памяти: O(n)
 */

fun sortTemperatures(inputName: String, outputName: String) {
    val posList = mutableListOf<Int>()
    val negList = mutableListOf<Int>()
    File(inputName).bufferedReader().readLines().forEach {
        val temperature = (it.toFloat() * 10).toInt()
        if (temperature >= 0)
            posList.add(temperature)
        else
            negList.add(temperature * (-1))
    }
    val posSort = countingSort(posList.toIntArray(), 5000)
    val negSort = countingSort(negList.toIntArray(), 2730).reversedArray()
    val posString = posSort.joinToString("\n") { (it.toFloat() / 10).toString()}
    val negString = negSort.joinToString("\n"){"-" + (it.toFloat() / 10).toString()}
    File(outputName).writeText(negString + "\n" + posString)
}

/**
 * Сортировка последовательности
 *
 * Средняя
 * (Задача взята с сайта acmp.ru)
 *
 * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
 *
 * 1
 * 2
 * 3
 * 2
 * 3
 * 1
 * 2
 *
 * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
 * а если таких чисел несколько, то найти минимальное из них,
 * и после этого переместить все такие числа в конец заданной последовательности.
 * Порядок расположения остальных чисел должен остаться без изменения.
 *
 * 1
 * 3
 * 3
 * 1
 * 2
 * 2
 * 2
 */

/**
 * n - количество чисел
 * Сложность по времени: O(n)
 * Сложность по памяти: O(2n) (хранение числа и количества его вхождений)
 */

fun sortSequence(inputName: String, outputName: String) {
    val a = HashMap<Int, Int>()
    File(inputName).bufferedReader().readLines().forEach {
        if (!a.containsKey(it.toInt()))
            a.put(it.toInt(), 0)
        else
            a[it.toInt()] = a[it.toInt()]!! + 1
    }
    val maxV = a.values.max()
    var maxK = Int.MAX_VALUE
    a.forEach {
        if (it.value == maxV)
            if (it.key < maxK)
                maxK = it.key
    }
    val bw = File(outputName).bufferedWriter()
    File(inputName).bufferedReader().readLines().forEach {
        if (it.toInt() != maxK)
            bw.write(it + "\n")
    }
    for (i in 1..maxV!!)
        bw.write(maxK.toString() + "\n")
    bw.write(maxK.toString())
    bw.close()
}

/**
 * Соединить два отсортированных массива в один
 *
 * Простая
 *
 * Задан отсортированный массив first и второй массив second,
 * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
 * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
 *
 * first = [4 9 15 20 28]
 * second = [null null null null null 1 3 9 13 18 23]
 *
 * Результат: second = [1 3 4 9 9 13 15 20 23 28]
 */
fun <T : Comparable<T>> mergeArrays(first: Array<T>, second: Array<T?>) {
    TODO()
}

