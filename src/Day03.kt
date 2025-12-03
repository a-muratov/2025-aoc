fun maxJoltage(batteryRow: String): Int {
    val batteryNumbers = batteryRow.map { it.toString().toInt() }

    val (leadingDigitIndex, leadingDigit) = batteryNumbers
        .take(batteryNumbers.size - 1)
        .withIndex()
        .maxBy { (_, v) -> v }

    val followingDigit = batteryNumbers.drop(leadingDigitIndex+1).max()

    return leadingDigit * 10 + followingDigit
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf(::maxJoltage)
    }

    fun part2(input: List<String>): Int {
        return input.size
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
        ) == 357
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

    //
    // check(part2(listOf(
    //     "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
    //         "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
    //         "824824821-824824827,2121212118-2121212124"
    // )) == 4174379265){
    //     (part2(listOf(
    //         "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
    //             "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
    //             "824824821-824824827,2121212118-2121212124"
    //     )).toString() + " != 4174379265")
    // }

    part1(input).println()
    part2(input).println()
}
