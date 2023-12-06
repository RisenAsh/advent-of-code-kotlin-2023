fun main() {
    data class Scratchcard(
        var card: Int = 0,
        var values: List<String> = emptyList(),
        var winningValues: List<String> = emptyList(),
    )

    fun parseScratchcard(input: List<String>): MutableList<Scratchcard> {
        val pile: MutableList<Scratchcard> = mutableListOf()

        input.forEach { line ->
            val scratchcard = Scratchcard()

            scratchcard.card = line.substringBefore(':').filter { it.isDigit() }.toInt()
            scratchcard.values = line.substringAfter(':').takeWhile { it != '|' }.split(" ").filter { it.isNotEmpty() }
            scratchcard.winningValues = line.substringAfter('|').split(" ").filter { it.isNotEmpty() }

            pile.add(scratchcard)
        }

        return pile
    }

    fun part1(input: List<String>): Int {
        var score = 0

        parseScratchcard(input).forEach { scratchcard ->
            var points = 0

            scratchcard.values.forEach { value ->
                if (value in scratchcard.winningValues) {
                    when (points) {
                        0 -> points++
                        else -> points += points
                    }
                }
            }
            score += points
        }

        return score
    }

    fun part2(input: List<String>): Int {
        val cards = parseScratchcard(input)
        val count = cards.indices.associateWith { 1 }.toMutableMap()

        cards.forEachIndexed { i, card ->
            val matches = card.values.intersect(card.winningValues.toSet()).size

            for (j in 1..matches) {
                count[i + j] = count[i + j]!! + count[i]!!
            }
        }

        return count.values.sum()
    }

    val testInput = readInput("Day04/P1_test")
    check(part1(testInput) == 13)

    check(part2(testInput) == 30)

    val input = readInput("Day04/P1")
    part1(input).println()
    part2(input).println()
}
