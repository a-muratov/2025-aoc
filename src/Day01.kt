import kotlin.math.absoluteValue
import kotlin.math.sign

data class Rotation(val s: String){
    val increment = (if (s[0] == 'L') -1 else 1) * (s.drop(1).toInt())
    val sign = increment.sign
    val abs = increment.absoluteValue
}

fun main() {
    fun part1(input: List<String>): Int {
        val rotations = input.map{
            Rotation(it)
        }
        var nZeros = 0
        var pos = 50
        rotations.forEach { r->
            pos = (pos + r.increment) % 100
            if (pos == 0) {
                nZeros += 1
            }
        }
        return nZeros
    }

    fun part2(input: List<String>): Int {
        val rotations = input.map{
            Rotation(it)
        }
        var nZeros = 0
        var pos = 50
        rotations.forEach { r->
            repeat(r.abs) {
                pos = (pos + r.sign) % 100
                if (pos == 0) {
                    nZeros += 1
                }
            }
        }
        return nZeros
    }

    // Test if implementation meets criteria from the description, like:
    val input = readInput("day01_input.txt")
    check(input.size == 4161)

    // Read the input from the `src/Day01.txt` file.
    part1(input).println()
    part2(input).println()
}
