fun main() {
    data class RGB(
        var r: Int,
        var g: Int,
        var b: Int,
    )

    data class Game(
        var index: Int,
        var rgb: RGB,
    )

    fun countCubes(games: List<String>): List<Game> {
        val result: MutableList<Game> = mutableListOf()

        games.forEach { round ->
            val game = Game(((round.split(":")[0]).split(" ")[1]).toInt(), RGB(0, 0, 0))
            val colours = (round.split(":")[1]).split(";", ",")

            colours.forEach { colour ->
                val colourTrimmed = (colour.trim()).split(" ")

                when (colourTrimmed[1]) {
                    "red" -> {
                        if (colourTrimmed[0].toInt() > game.rgb.r) {
                            game.rgb.r = colourTrimmed[0].toInt()
                        }
                    }

                    "green" -> {
                        if (colourTrimmed[0].toInt() > game.rgb.g) {
                            game.rgb.g = colourTrimmed[0].toInt()
                        }
                    }

                    "blue" -> {
                        if (colourTrimmed[0].toInt() > game.rgb.b) {
                            game.rgb.b = colourTrimmed[0].toInt()
                        }
                    }
                }
            }
            result.add(game)
        }
        return result
    }

    fun part1(
        input: List<String>,
        cubes: RGB,
    ): Int {
        val result: MutableList<Int> = mutableListOf()

        countCubes(input).forEach { game ->
            if (game.rgb.r <= cubes.r && game.rgb.g <= cubes.g && game.rgb.b <= cubes.b) {
                result.add(game.index)
            }
        }

        return result.sum()
    }

    fun part2(input: List<String>): Int {
        val result: MutableList<Int> = mutableListOf()

        countCubes(input).forEach { game ->
            result.add(game.rgb.r * game.rgb.g * game.rgb.b)
        }

        return result.sum()
    }

    val testInput1 = readInput("Day02/P1_test")
    check(part1(testInput1, RGB(12, 13, 14)) == 8)

    val testInput2 = readInput("Day02/P1_test")
    check(part2(testInput2) == 2286)

    val input = readInput("Day02/P1")
    part1(input, RGB(12, 13, 14)).println()
    part2(input).println()
}
