import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        val freshRangesWithIndex = input.withIndex().takeWhile{(i, v) -> v != ""}
        val ids = input.drop(freshRangesWithIndex.size + 1).map{it.toLong()}
        val freshRanges = freshRangesWithIndex.map{(i, v) -> v.split("-").map{it.toLong()}}.map{l -> LongRange(l[0], l[1])}
        check(ids.size + freshRanges.size + 1 == input.size)

        return ids.count{ id -> freshRanges.any{ range -> id in range}}
    }

    fun part2(input: List<String>): Long {
        val freshRangesWithIndex = input.withIndex().takeWhile{(i, v) -> v != ""}
        val ids = input.drop(freshRangesWithIndex.size + 1).map{it.toLong()}
        val freshRanges = freshRangesWithIndex.map{(i, v) -> v.split("-").map{it.toLong()}}.map{l -> LongRange(l[0], l[1])}

        val sortedRanges = freshRanges.sortedBy { it.first }
        var freshCount = 0L
        var rangeIdx = 0
        val runningRanges = mutableListOf<LongRange>()
        while (rangeIdx < sortedRanges.size){
            val nextRangeToAdd = sortedRanges[rangeIdx]
            var runningMin = nextRangeToAdd.start
            var runningMax = nextRangeToAdd.endInclusive
            while (rangeIdx + 1 < sortedRanges.size && sortedRanges[rangeIdx + 1].start <= runningMax){
                rangeIdx += 1
                runningMax = max(runningMax, sortedRanges[rangeIdx].endInclusive)
            }
            freshCount += runningMax - runningMin + 1
            rangeIdx += 1
        }
        return freshCount
    }

    val input = readInput("day05_input.txt")
    check(input.size == 1170)

    check(
        part1(
            listOf(
                "3-5",
                "10-14",
                "16-20",
                "12-18",
                "",
                "1",
                "5",
                "8",
                "11",
                "17",
                "32"
            )
        ) == 3
    ) {
        part1(
            listOf(
                "3-5",
                "10-14",
                "16-20",
                "12-18",
                "",
                "1",
                "5",
                "8",
                "11",
                "17",
                "32"
            )
        ).toString()
    }

    check(
        part2(
            listOf(
                "3-5",
                "10-14",
                "16-20",
                "12-18",
                "",
                "1",
                "5",
                "8",
                "11",
                "17",
                "32"
            )
        ) == 14L
    ) {
        part2(
            listOf(
                "3-5",
                "10-14",
                "16-20",
                "12-18",
                "",
                "1",
                "5",
                "8",
                "11",
                "17",
                "32"
            )
        ).toString()
    }

    part1(input).println()
    part2(input).println()
}
