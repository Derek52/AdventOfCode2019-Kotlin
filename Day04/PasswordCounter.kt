import kotlin.collections.Set

fun main(args : Array<String>) {

	val inputMin = 357253
	val inputMax = 892942

	var validPasswords = 0
	for (i in inputMin..inputMax) {
		if (isValidPassword(i.toString())) {
			validPasswords++
			println(i)
		}
	}
	println(validPasswords)
}

fun isValidPassword(password : String) : Boolean {
	val characterSet = mutableSetOf<Char>()

	var hasRepeatingDigit = false

	var previousDigit = 0
	for (c in password) {
		//don't bother checking for a duplicate, if we have already found one, or if we are on the first digit
		if (characterSet.isNotEmpty() && hasRepeatingDigit == false) {
			if (characterSet.contains(c)) {
				hasRepeatingDigit = true
			}
		}
		if (c.toInt() < previousDigit) {
			return false
		}

		characterSet.add(c)
		previousDigit = c.toInt()
	}

	//if we've made it this far, we have no decreasing digits, and it all depends on if we have found a repeating digit or not.
	return hasRepeatingDigit
}
