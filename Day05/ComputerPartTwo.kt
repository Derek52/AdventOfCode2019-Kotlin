import java.util.*

enum class OpCodes {
	ADD, MULTIPLY, INPUT, OUTPUT, IF_TRUE, IF_FALSE, LESS_THAN, EQUALS, HALT
}

//this stores the parameter modes for the 3 parameters that come after the opcode
data class ParameterModes(val one : Char = '0', val two : Char = '0', val three : Char = '0')

//this stores our 3 parameter values
data class mathParameters(val a : Int, val b : Int, val c : Int)

fun main(args : Array<String>) {

	//var input = arrayOf(1002, 4, 3, 4, 33)

	//val input = arrayOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9)
	//val input = arrayOf(3,9,8,9,10,9,4,9,99,-1,8)
	//val input = arrayOf(3,9,7,9,10,9,4,9,99,-1,8)
	//val input = arrayOf(3,3,1108,-1,8,3,4,3,99)
	//val input = arrayOf(3,3,1107,-1,8,3,4,3,99)
	//val input = arrayOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99)

	val input = arrayOf(3,225,1,225,6,6,1100,1,238,225,104,0,1001,92,74,224,1001,224,-85,224,4,224,1002,223,8,223,101,1,224,224,1,223,224,223,1101,14,63,225,102,19,83,224,101,-760,224,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,1101,21,23,224,1001,224,-44,224,4,224,102,8,223,223,101,6,224,224,1,223,224,223,1102,40,16,225,1102,6,15,225,1101,84,11,225,1102,22,25,225,2,35,96,224,1001,224,-350,224,4,224,102,8,223,223,101,6,224,224,1,223,224,223,1101,56,43,225,101,11,192,224,1001,224,-37,224,4,224,102,8,223,223,1001,224,4,224,1,223,224,223,1002,122,61,224,1001,224,-2623,224,4,224,1002,223,8,223,101,7,224,224,1,223,224,223,1,195,87,224,1001,224,-12,224,4,224,1002,223,8,223,101,5,224,224,1,223,224,223,1101,75,26,225,1101,6,20,225,1102,26,60,224,101,-1560,224,224,4,224,102,8,223,223,101,3,224,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,108,677,226,224,102,2,223,223,1006,224,329,1001,223,1,223,1108,226,677,224,1002,223,2,223,1006,224,344,101,1,223,223,7,226,677,224,102,2,223,223,1006,224,359,1001,223,1,223,1007,226,677,224,1002,223,2,223,1006,224,374,1001,223,1,223,1108,677,226,224,102,2,223,223,1005,224,389,1001,223,1,223,107,226,226,224,102,2,223,223,1006,224,404,101,1,223,223,1107,226,226,224,1002,223,2,223,1005,224,419,1001,223,1,223,1007,677,677,224,102,2,223,223,1006,224,434,101,1,223,223,1107,226,677,224,1002,223,2,223,1006,224,449,101,1,223,223,107,677,677,224,102,2,223,223,1005,224,464,1001,223,1,223,1008,226,226,224,1002,223,2,223,1005,224,479,101,1,223,223,1007,226,226,224,102,2,223,223,1005,224,494,1001,223,1,223,8,677,226,224,1002,223,2,223,1005,224,509,1001,223,1,223,108,677,677,224,1002,223,2,223,1005,224,524,1001,223,1,223,1008,677,677,224,102,2,223,223,1006,224,539,1001,223,1,223,7,677,226,224,1002,223,2,223,1005,224,554,101,1,223,223,1108,226,226,224,1002,223,2,223,1005,224,569,101,1,223,223,107,677,226,224,102,2,223,223,1005,224,584,101,1,223,223,8,226,226,224,1002,223,2,223,1005,224,599,101,1,223,223,108,226,226,224,1002,223,2,223,1006,224,614,1001,223,1,223,7,226,226,224,102,2,223,223,1006,224,629,1001,223,1,223,1107,677,226,224,102,2,223,223,1005,224,644,101,1,223,223,8,226,677,224,102,2,223,223,1006,224,659,1001,223,1,223,1008,226,677,224,1002,223,2,223,1006,224,674,1001,223,1,223,4,223,99,226)

	operateComputer(input)
}

fun operateComputer(input : Array<Int>) {
	var running = true
	var i = 0
	while(running) {
		val command = input[i].toString()
		val opCode = getOpCode(command)
		val parameterModes = getParameterModes(command)
		println("$command + $opCode + $i")
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
				val userInput = readLine()!!.toInt()
				if (parameterModes.one == '0') {
					input[input[i+1]] = userInput
				} else {
					input[i+1] = userInput
				}
				i += 2
			}
			OpCodes.OUTPUT -> {
				if (parameterModes.one == '0') {
					println("Answer" + input[input[i+1]])
				} else {
					println("Answer" + input[i+1])
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
			return
		}
	}
}

fun getOpCode(command : String) : OpCodes {
	var opCode : String = ""
	if (command.length > 1) {
		opCode = command.substring(command.lastIndex -1) 
	} else {
		opCode = command
	}
	println("opCode: $opCode")
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
