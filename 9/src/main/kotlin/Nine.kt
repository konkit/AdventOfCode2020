import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val preambleLength = 25

    val numbers = File("input").readLines().map { v -> v.toLong() }

    println(getNumber(numbers, preambleLength))
}

fun getNumber(numbers: List<Long>, preambleLength: Int): Long {
    val queue = ArrayDeque<Long>(preambleLength)

    var i = 0;

    while(i < numbers.size) {
        if (queue.size == preambleLength) {
            if (!isSum(queue.toList(), numbers[i])) {
                return numbers[i]
            }

            queue.pop()
        }

        queue.add(numbers[i])

        i += 1
    }

    return -1
}

fun isSum(array: List<Long>, number: Long): Boolean {
    var start = 0
    var end = array.size -1

    val sortedArray = array.sorted()

    while(start != end) {
        val currentSum = sortedArray[start] + sortedArray[end]
        if (currentSum == number) {
            return true
        } else if (currentSum > number) {
            end -= 1
        } else if (currentSum < number) {
            start += 1
        }
    }

    return false
}
