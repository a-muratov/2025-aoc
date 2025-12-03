fun maxJoltage(batteryNumbers: List<Long>, nDigits: Long): Long {
    if (batteryNumbers.isEmpty())
        throw IllegalArgumentException()

    if (nDigits == 1L) {
        return batteryNumbers.max()
    }

    val (leadingDigitIndex, leadingDigit) = batteryNumbers
        .take(batteryNumbers.size - nDigits.toInt() + 1)
        .withIndex()
        .maxBy { (_, v) -> v }

    return leadingDigit * 10L.power(nDigits-1) +
        maxJoltage(batteryNumbers.drop(leadingDigitIndex + 1), nDigits - 1)
}

fun main() {
    fun part1(input: List<String>): Long {
        return input.sumOf { maxJoltage(it.toListOfLongs(), 2) }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { maxJoltage(it.toListOfLongs(), 12) }
    }

    val input = readInput("day03_input.txt")
    check(input.size == 200)

    check(
        part1(
            listOf(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
            )
        ) == 357L
    ) {
        part1(
            listOf(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
            )
        ).toString()
    }

    part1(input).println()
    part2(input).println()
}
