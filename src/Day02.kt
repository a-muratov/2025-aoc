import kotlin.math.pow
import kotlin.math.roundToLong

data class CompositeRange(
    val s: String
) {
    val stringFrom = s.split('-').first()
    val stringTo = s.split('-').last()
    val lengthFrom = stringFrom.length
    val lengthTo = stringTo.length

    val subRanges = initSubRanges(s)

    fun initSubRanges(s: String): List<SimpleRange> {
        if (lengthFrom > lengthTo) {
            throw IllegalArgumentException()
        } else if (lengthFrom == lengthTo) {
            return listOf(SimpleRange(s))
        } else {
            check(lengthFrom < lengthTo)
            val result = mutableListOf(
                SimpleRange(stringFrom + "-" + "9".repeat(lengthFrom))
            )

            (lengthFrom..lengthTo-2).forEach { length ->
                result.add(SimpleRange("1"+"0".repeat(length) + "-" + "9".repeat(length+1)))
            }

            result.add(
                SimpleRange("1"+"0".repeat(lengthTo-1) + "-" + stringTo)
            )
            return result
        }
    }
    val sumFaulty = subRanges.sumOf {it.sumFaulty() }
    val sumDoubleFaulty = subRanges.sumOf {it.sumDoubleFaulty() }
}

data class SimpleRange(
    val s: String
) {
    val from = s.split('-').first().toLong()
    val to = s.split('-').last().toLong()

    val length = s.split('-').first().length

    fun sumFaulty(): Long{
        if(length % 2 == 1){
            return 0
        }
        val halfLength = length / 2

        val firstFrom = from.toString().take(halfLength).toLong()
        val firstTo = to.toString().take(halfLength).toLong()
        val multiplier = ("1" + "0".repeat(halfLength)).toLong()

        return (firstFrom..firstTo).sumOf {
            if (it * multiplier + it in from..to) it * multiplier + it else 0
        }
    }

    fun sumDoubleFaulty(): Long {
        return (from..to).filter{isDoubleFaulty(it)}.sum()
    }
}

fun isFaulty(number: Long): Boolean{
    val length = number.toString().length
    if(length % 2 == 1)
        return false
    val halfLength = length / 2
    return number.toString().take(halfLength) == number.toString().drop(halfLength)
}

fun isDoubleFaulty(number: Long): Boolean{
    val s = number.toString()
    val n = s.length
    (1..n/2).forEach{
        if(n % it == 0 && s == s.take(it).repeat(n / it)){
            return true
        }
    }
    return false
}

fun main() {
    fun part1(input: List<String>): Long {
        val ranges = input.first().split(',').map(::CompositeRange)
        return ranges.sumOf{it.sumFaulty}
    }

    fun part2(input: List<String>): Long {
        val ranges = input.first().split(',').map(::CompositeRange)
        return ranges.sumOf{it.sumDoubleFaulty}
    }

    // Test if implementation meets criteria from the description, like:
    val input = readInput("day02_input.txt")
    check(input.size == 1)

    check(part1(listOf("11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                    "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                    "824824821-824824827,2121212118-2121212124")) == 1227775554L){
        part1(listOf("11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
            "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
            "824824821-824824827,2121212118-2121212124")).toString() + " != 1227775554L"
    }

    check(part2(listOf(
        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
            "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
            "824824821-824824827,2121212118-2121212124"
    )) == 4174379265){
        (part2(listOf(
            "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124"
        )).toString() + " != 4174379265")
    }

    part1(input).println()
    part2(input).println()
}
