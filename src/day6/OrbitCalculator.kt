package day6

import java.io.File

fun main() {
    val input = File("src/day6/input.txt").readLines()
    println(OrbitMap().getTotalNumberOfOrbits(input))
}

class OrbitMap {

    val orbitmap: MutableMap<String, OrbitNode> = mutableMapOf()

    fun getTotalNumberOfOrbits(inputList: List<String>) : Int {
        buildOrbitMap(inputList)
        var totalOrbits = 0
        orbitmap.values.forEach { totalOrbits += it.calculateOrbits() }
        return totalOrbits
    }

    private fun buildOrbitMap(inputList: List<String>) {
        inputList.forEach{
            val splitString = it.split(')')
            val parentName = splitString[0]
            val childName = splitString[1]
            val parentNode = orbitmap[parentName]
            if (parentNode != null) {
                val childNode = OrbitNode(childName)
                parentNode.addChild(childNode)
                orbitmap[childName] = childNode
            } else {
                val newParentNode = OrbitNode(parentName)
                val childNode = OrbitNode(childName)
                newParentNode.addChild(childNode)
                orbitmap[parentName] = newParentNode
                orbitmap[childName] = childNode
            }
        }
    }
}

class OrbitNode(var name: String) {
    var parent:OrbitNode? = null
    var children: MutableList<OrbitNode> = mutableListOf()

    fun addChild(orbitNode:OrbitNode) {
        orbitNode.parent = this
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
}