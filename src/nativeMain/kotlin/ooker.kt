import platform.posix.fflush
import platform.posix.fprintf

fun main(args: Array<String>) {
  val string = when {
      args.isEmpty() -> readLine().orEmpty()
      args.size == 1 -> args[0]
      else -> args.joinToString(" ")
  }

  // Define map for coding
  val codeMap = mapOf("a" to "oO", "b" to "Oooo", "c" to "OoOo", "d" to "Ooo", "e" to "o", "f" to "ooOo", "g" to "OOo",
  "h" to "oooo", "i" to "oo", "j" to "oOOO", "k" to "OoO", "l" to "oOoo", "m" to "OO", "n" to "Oo",
  "o" to "OOO", "p" to "oOOo", "q" to "OOoO", "r" to "oOo", "s" to "ooo", "t" to "O", "u" to "ooO",
  "v" to "oooO", "w" to "oOO", "x" to "OooO", "y" to "OoOO", "z" to "OOoo", "0" to "OOOOO",
  "1" to "oOOOO", "2" to "ooOOO", "3" to "oooOO", "4" to "ooooO", "5" to "ooooo", "6" to "Ooooo",
  "7" to "OOooo", "8" to "OOOoo", "9" to "OOOOo", " " to "k ")

  val output = when {
    string.matches(Regex("([Oo0k\\s])+")) -> // Decode
      string.dropLast(1).split('0').map { codeMap.getKey(it) }.joinToString("")
    else -> // Encode
      string.map { codeMap.getOrElse(it.toLowerCase().toString()) {""}}.joinToString("0").plus("k")
  }

  println(output)
}

// Printing errors
val STDERR = platform.posix.fdopen(2, "w")
fun printErr(message: String) {
  fprintf(STDERR, message + "\n")
  fflush(STDERR)
}

// Extension fn for reverse map lookup
fun <K, V> Map<K, V>.getKey(value: V) = entries.firstOrNull { it.value == value }?.key
