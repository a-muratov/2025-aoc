data class RollGrid(val input: List<String>) {
    val width = input[0].length
    val height = input.size
    val grid = input.map { it.toList() }
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

    fun nAccessibleRolls() = (0..<height).sumOf { i ->
        (0..<width).count { j ->
            isRoll(i, j) && nNeighborRolls(i, j) < 4
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val rollGrid = RollGrid(input)
        return rollGrid.nAccessibleRolls()
    }

    fun part2(input: List<String>): Int {
        return input.size
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

    part1(input).println()
    part2(input).println()
}
