fun main() {
    data class Index2D(
        val x: Int,
        val y: Int,
    )

    data class Engine(
        var partNumbers: Int,
        var gearRatio: Int,
    )

    fun analyseEngine(input: List<String>): Engine {
        val engine = Engine(0, 0)
        val asterisks = mutableMapOf<Index2D, MutableList<Int>>()

        input.forEachIndexed { y, line ->
            var x = 0

            while (x < line.length) {
                if (!line[x].isDigit()) {
                    x++
                    continue
                }

                val number = line.substring(x).takeWhile { it.isDigit() }

                val top = (x..<x + number.length).map { Index2D(it, y - 1) }
                val bottom = (x..<x + number.length).map { Index2D(it, y + 1) }
                val left = (y - 1..y + 1).map { Index2D(x - 1, it) }
                val right = (y - 1..y + 1).map { Index2D(x + number.length, it) }

                var counted = false

                listOf(top, bottom, left, right).flatten().filter { it.y in input.indices && it.x in line.indices }
                    .forEach { pos ->
                        if (input[pos.y][pos.x] != '.' && !input[pos.y][pos.x].isDigit() && !counted) {
                            engine.partNumbers += number.toInt()
                            counted = true
                        }
                        if (input[pos.y][pos.x] == '*') {
                            asterisks.getOrPut(pos) { mutableListOf() }.add(number.toInt())
                        }
                    }

                x += number.length
            }
        }

        engine.gearRatio = asterisks.filterValues { it.size == 2 }.values.sumOf { it.reduce { i, j -> i * j } }

        return engine
    }

    val testInput = readInput("Day03/P1_test")
    val testEngine = analyseEngine(testInput)

    check(testEngine.partNumbers == 4361)
    check(testEngine.gearRatio == 467835)

    val input = readInput("Day03/P1")
    val engine = analyseEngine(input)

    engine.partNumbers.println()
    engine.gearRatio.println()
}
