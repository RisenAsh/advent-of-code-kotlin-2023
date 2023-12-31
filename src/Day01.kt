fun main() {
    fun formatValue(string: String): Int {
        var value = string.filter { it.isDigit() }

        when {
            value.length < 2 -> {
                value += value
            }
            value.length > 2 -> {
                value = "${value.first()}${value.last()}"
            }
        }

        return value.toInt()
    }

    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            result += formatValue(line)
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val digits =
            mapOf(
                "one" to "o1e",
                "two" to "t2o",
                "three" to "t3e",
                "four" to "f4r",
                "five" to "f5e",
                "six" to "s6x",
                "seven" to "s7n",
                "eight" to "e8t",
                "nine" to "n9e",
            )

        input.forEach { value ->
            var string = value

            digits.forEach { digit ->
                string = string.replace(digit.key, digit.value)
            }

            result += formatValue(string)
        }

        return result
    }

    val testInput1 = readInput("Day01/P1_test")
    check(part1(testInput1) == 142)

    val testInput2 = readInput("Day01/P2_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01/P1")
    part1(input).println()
    part2(input).println()
}
