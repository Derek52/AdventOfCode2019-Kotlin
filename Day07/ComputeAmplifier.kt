import java.util.*

enum class OpCodes {
	ADD, MULTIPLY, INPUT, OUTPUT, IF_TRUE, IF_FALSE, LESS_THAN, EQUALS, HALT
}

//this stores the parameter modes for the 3 parameters that come after the opcode
data class ParameterModes(val one : Char = '0', val two : Char = '0', val three : Char = '0')

//this stores our 3 parameter values
data class mathParameters(val a : Int, val b : Int, val c : Int)

var highestAmpOutput = 0

val program = arrayOf(3,8,1001,8,10,8,105,1,0,0,21,38,63,72,85,110,191,272,353,434,99999,3,9,102,4,9,9,101,2,9,9,102,3,9,9,4,9,99,3,9,1001,9,4,9,102,2,9,9,1001,9,5,9,1002,9,5,9,101,3,9,9,4,9,99,3,9,1001,9,2,9,4,9,99,3,9,1001,9,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,102,2,9,9,1001,9,2,9,1002,9,4,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99)

//val program = arrayOf(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)

fun main(args : Array<String>) {
	genPermutations(arrayOf(0, 1, 2, 3, 4), 5)
	println(highestAmpOutput)
	//println(testPermutation(arrayOf(4, 3, 2, 1, 0)))
	//val previousOutput = operateComputer(program.copyOf(), arrayOf(3, 4))
	//println(operateComputer(program.copyOf(), arrayOf(2, previousOutput)))
}

fun genPermutations(options : Array<Int>, x : Int) {
	if (x == 1) {
		val ampOutput = testPermutation(options)
		println(ampOutput)
		if (ampOutput > highestAmpOutput) {
			highestAmpOutput = ampOutput
		}
	} else {
		for (i in 0..x - 1) {
			genPermutations(options, x - 1)
			if (x % 2 == 1) {
				swap(options, 0, x - 1)
			} else {
				swap(options, i, x - 1)
			}
		}
	}
}

fun swap(array : Array<Int>, a : Int, b : Int) {
	val temp = array[a]
	array[a] = array[b]
	array[b] = temp
}

fun testPermutation(permutation : Array<Int>) : Int{
	var totalOutput = 0
	var previousOutput = 0
	for (i in permutation) {
		previousOutput = operateComputer(program.copyOf(), arrayOf(i, previousOutput))
		//println(previousOutput)
	}
	return previousOutput
}

fun operateComputer(input : Array<Int>, programInput : Array<Int>) : Int {
	var running = true
	var i = 0
	var output = 0
	var inputIndex = 0
	while(running) {
		val command = input[i].toString()
		val opCode = getOpCode(command)
		val parameterModes = getParameterModes(command)
		when (opCode) {
			OpCodes.ADD -> {
				var a = 0
				var b = 0
				if (parameterModes.one == '0') {
					a = input[input[i + 1]]
				} else {
					a = input[i + 1]
				}

				if (parameterModes.two == '0') {
					b = input[input[i + 2]]
				} else {
					b = input[i + 2]
				}

				if (parameterModes.three == '0') {
					input[input[i + 3]] = a + b
				} else {
					input[i + 3] = a + b
				}
				i += 4
			}
			OpCodes.MULTIPLY -> {
				var a = 0
				var b = 0
				if (parameterModes.one == '0') {
					a = input[input[i + 1]]
				} else {
					a = input[i + 1]
				}

				if (parameterModes.two == '0') {
					b = input[input[i + 2]]
				} else {
					b = input[i + 2]
				}

				if (parameterModes.three == '0') {
					input[input[i + 3]] = a * b
				} else {
					input[i + 3] = a * b
				}
				i += 4
			}
			OpCodes.INPUT -> {
				if (parameterModes.one == '0') {
					input[input[i+1]] = programInput[inputIndex]
				} else {
					input[i+1] = programInput[inputIndex]
				}
				inputIndex++
				i += 2
			}
			OpCodes.OUTPUT -> {
				if (parameterModes.one == '0') {
					output = input[input[i+1]]
				} else {
					output = input[i+1]
				}
				i += 2
			}
			OpCodes.IF_TRUE -> {
				var a = 0
				if (parameterModes.one == '0') {
					a = input[input[i + 1]]
				} else {
					a = input[i + 1]
				}
				if (a != 0) {
					if (parameterModes.two == '0') {
						i = input[input[i + 2]]
					} else {
						i = input[i + 2]
					}
				} else {
					i += 3
				}
			}
			OpCodes.IF_FALSE -> {
				//println("$parameterModes $i")
				var a = 0
				if (parameterModes.one == '0') {
					a = input[input[i + 1]]
				} else {
					a = input[i + 1]
				}
				//println("$a right before equality check")
				if (a == 0) {
					if (parameterModes.two == '0') {
						//println("parameterMOdes.two = 0")
						i = input[input[i + 2]]
					} else {
						i = input[i + 2]
					}
				} else {
					i += 3
				}
				//println("after: $i, a = $a")
			}
			OpCodes.LESS_THAN -> {
				var a = 0
				var b = 0
				var c = 0
				if (parameterModes.one == '0') {
					a = input[input[i + 1]]
				} else {
					a = input[i + 1]
				}

				if (parameterModes.two == '0') {
					b = input[input[i + 2]]
				} else {
					b = input[i + 2]
				}
				if (parameterModes.three == '0') {
					c = input[i + 3]
				} else {
					c = i + 3
				}
				if (a < b) {
					input[c] = 1
				} else {
					input[c] = 0
				}
				i += 4
			}
			OpCodes.EQUALS -> {
				var a = 0
				var b = 0
				var c = 0
				if (parameterModes.one == '0') {
					a = input[input[i + 1]]
				} else {
					a = input[i + 1]
				}
				if (parameterModes.two == '0') {
					b = input[input[i + 2]]
				} else {
					b = input[i + 2]
				}
				if (parameterModes.three == '0') {
					c = input[i + 3]
				} else {
					c = i + 3
				}
				println("a: $a, b: $b, c: $c")

				if (a == b) {
					input[c] = 1
				} else {
					input[c] = 0
				}
				i += 4
			}
			OpCodes.HALT -> running = false
		}
		if (i >= input.size) {
			return 0
		}
	}
	return output
}

fun getOpCode(command : String) : OpCodes {
	var opCode : String = ""
	if (command.length > 1) {
		opCode = command.substring(command.lastIndex -1) 
	} else {
		opCode = command
	}
	return when(opCode) {
		"1", "01" -> OpCodes.ADD
		"2", "02" -> OpCodes.MULTIPLY
		"3", "03" -> OpCodes.INPUT
		"4", "04" -> OpCodes.OUTPUT
		"5", "05" -> OpCodes.IF_TRUE
		"6", "06" -> OpCodes.IF_FALSE
		"7", "07" -> OpCodes.LESS_THAN
		"8", "08" -> OpCodes.EQUALS
		 else -> OpCodes.HALT
	}
}

fun getParameterModes(command : String) : ParameterModes {
	return when (command.length) {
		//since i used default values I could just use named paramters here, to omit the 0's. It's not my preference for this type of thing though.
		//the line would look like this.
		//3 -> ParameterModes(three = command[0])
		3 -> ParameterModes(command[0])
		4 -> ParameterModes(command[1], command[0], '0')
		5 -> ParameterModes(command[2], command[1], command[0])
		else -> ParameterModes()
	}
}

fun calculateFinalAnswer(noun : Int, verb : Int) : Int {
	return 100 * noun + verb
}
