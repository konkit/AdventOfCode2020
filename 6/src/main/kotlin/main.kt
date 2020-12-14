import java.io.File

fun main() {
    val perGroup = File("input").readText()
        .split("\n\n")
        .map {
            it.split("\n")
                .filter { it.isNotBlank() }
        }

    val results = perGroup.map { group ->
        val letterPairs = group.flatMap { person -> person.toCharArray().toList() }

        fun withAddedChar(map: Map<Char, Int>, c: Char): Map<Char, Int> {
            val currentValue = map[c] ?: 0
            return map + (c to currentValue + 1)
        }

        letterPairs
            .fold(emptyMap(), ::withAddedChar)
            .filterValues { v -> v == group.size }.size
    }

    println(results.sum())
}
