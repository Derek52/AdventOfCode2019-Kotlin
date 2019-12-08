import kotlin.collections.HashMap
import kotlin.collections.HashSet
import java.util.Stack

class Planet(val name : String, var visited : Boolean = false)

class OrbitGraph() {
	val orbitMap : HashMap<Planet, HashSet<Planet>> = HashMap()

	fun addEdge(source : Planet, destination : Planet) {
		orbitMap.computeIfAbsent(source) { HashSet() }
		.add(destination)
		orbitMap.computeIfAbsent(destination) { HashSet() }
		.add(source)
	}
}

fun main(args : Array<String>) {
	//i used a vim command to change newlines into commas, letting me put the whole input in as one long string
	// :21,30s/\n/,/g //change 21-30 to your own line numbers if you want to use this
	val input = "COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L"
	
	val orbitGraph = OrbitGraph()

	val links = input.split(",")
	for (link in links) {
		val planetNames = link.split(")")
		orbitGraph.addEdge(Planet(planetNames[0]), Planet(planetNames[1]))
	}

	println(findNumberOfOrbits)
}

fun findNumberOfOrbits(orbitGraph : OrbitGraph) {
	//this is basically just a depth first search
	val stack = Stack<Planet>()
	stack.push
}
