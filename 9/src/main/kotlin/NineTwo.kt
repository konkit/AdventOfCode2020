import java.io.File

fun main(args: Array<String>) {
    val preambleLength = 25

    val numbers = File("input").readLines().map { v -> v.toLong() }

    val target = 542529149L
//    val target = 127L

    val targetIndexes = getTargetIndexes(numbers, target)

    val targetNumbers = targetIndexes.map { i -> numbers[i] }.sorted()

    println(targetNumbers.first() + targetNumbers.last())
}

fun getTargetIndexes(numbers: List<Long>, target: Long): List<Int> {
    val maxIndex = numbers.size - 1

    for (i in 0..maxIndex) {
        var j = i

        var cntSum: Long = 0
        var indexes = mutableListOf<Int>()

        while (j < maxIndex) {
            cntSum += numbers[j]

            if (cntSum > target) {
                break
            } else if (cntSum == target) {
                return indexes
            }

            indexes.add(j)
            j += 1
        }
    }

    return emptyList<Int>()
}


