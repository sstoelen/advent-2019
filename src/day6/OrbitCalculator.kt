package day6

import java.io.File

fun main() {
    val input = File("src/day6/input.txt").readLines()
    println(OrbitMap().getTotalNumberOfOrbits(input))
}

class OrbitMap {

    val orbitmap: MutableMap<String, MutableSet<String>> = mutableMapOf()

    fun getTotalNumberOfOrbits(inputList: List<String>) : Int {
        buildOrbitMap(inputList)
        println(orbitmap)
        var totalOrbits = 0
        orbitmap.keys.forEach { totalOrbits += calculateAllOrbits(it) }
        return totalOrbits
    }

    private fun calculateAllOrbits(planetName: String) : Int {
        return if (orbitmap[planetName] == null) {
            0
        } else {
            var orbits = orbitmap[planetName]!!.size
            orbitmap[planetName]!!.forEach {
                orbits += calculateAllOrbits(it)
            }
            orbits
        }


    }

    private fun buildOrbitMap(inputList: List<String>) {
        inputList.forEach{
            val splitString = it.split(')')
            val centerPlanet = splitString[0]
            val planetThatOrbits = splitString[1]
            val setOfPlanetsThatOrbits = orbitmap[centerPlanet]
            if (setOfPlanetsThatOrbits == null) {
                val newSetOfPlanetsThatOrbit = mutableSetOf(planetThatOrbits)
                orbitmap[centerPlanet] = newSetOfPlanetsThatOrbit
            } else {
                setOfPlanetsThatOrbits.add(planetThatOrbits)
            }
        }
    }
}

/*
class OrbitNode(var name: String) {
    var parents: MutableList<OrbitNode> = mutableListOf()
    var children: MutableList<OrbitNode> = mutableListOf()

    fun addChild(orbitNode:OrbitNode) {
        orbitNode.parents = this
        children.add(orbitNode)
    }

    fun calculateOrbits() : Int {
        return if (children.isEmpty()) {
            0
        } else {
            var orbits = children.size
            children.forEach{
                orbits += it.calculateOrbits()
            }
            orbits
        }
    }

    override fun toString(): String {
        var s = name
        if (!children.isEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}*/
