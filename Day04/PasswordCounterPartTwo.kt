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

	var currentMatch = password[0]
	var matchLength = 1
	for (i in 1..password.length - 1) {
		println("${password[i]} $currentMatch $matchLength") 
		if (password[i].equals(currentMatch)) {
			matchLength++
			println("incrementing matchLength")
		} else{
			//we no longer have a match. if our match length is 2, we are done. if it is over 2, we need to reset matchLength and keep running the loop
			if (matchLength == 2) {
				return true
			} else {
				matchLength = 1
			}
		}
		currentMatch = password[i]
	}
	//this is a fun edge case. the previous loop won't return true if the 2 repeating digits are the last 2 of the password, so we have to check it here
	if (matchLength == 2) {
		return true
	}

	//with the new rule and the new loop, if we make it this far, our password is invalid.
	return false
}
