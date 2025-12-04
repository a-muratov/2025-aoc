data class RollGrid(val input: List<String>) {
    val width = input[0].length
    val height = input.size
    val grid = input.map { it.toMutableList() }
    fun isRoll(i: Int, j: Int) = (grid[i][j] == '@')
    fun neighbors(i: Int, j: Int) =
        listOf(
            (-1 to -1),
            (-1 to 0),
            (-1 to 1),
            (0 to -1),
            (0 to 1),
            (1 to -1),
            (1 to 0),
            (1 to 1)
        ).map {
            i + it.first to j + it.second
        }.filter {
            it.first in 0..<height && it.second in 0..<width
        }

    fun nNeighborRolls(i: Int, j: Int) = neighbors(i, j).count { isRoll(it.first, it.second) }

    fun accessibleRolls() = (0..<height).flatMap { i ->
        (0..<width).map { j ->
            i to j
        }
    }.filter{(i, j) -> isRoll(i, j) && nNeighborRolls(i, j) < 4}

    fun removeAccessibleRolls(): Int {
        val accessibleRolls = accessibleRolls()
        accessibleRolls.forEach { (i, j) -> grid[i][j] = '.' }
        return accessibleRolls.size
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val rollGrid = RollGrid(input)
        return rollGrid.accessibleRolls().size
    }

    fun part2(input: List<String>): Int {
        val rollGrid = RollGrid(input)
        var nRollsRemoved = 0
        do {
            val moreRollsRemoved = rollGrid.removeAccessibleRolls()
            nRollsRemoved += moreRollsRemoved
        } while (moreRollsRemoved > 0)
        return nRollsRemoved
    }

    val input = readInput("day04_input.txt")
    check(input.size == 139)

    check(
        part1(
            listOf(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
            )
        ) == 13
    ) {
        part1(
            listOf(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
            )
        ).toString()
    }

    check(
        part2(
            listOf(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
            )
        ) == 43
    ) {
        part2(
            listOf(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
            )
        ).toString()
    }

    part1(input).println()
    part2(input).println()
}
