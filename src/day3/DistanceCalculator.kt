package day3

fun main() {
    DistanceCalculator().calculateDistanceForWires()
}

class DistanceCalculator {

    fun calculateDistanceForWires() {
        val grid = Grid()
        grid.addLine(1, "D2")
        grid.addLine(1, "D3")
        println(grid.map)
    }

}

class Grid {
    val map = mutableMapOf<Position, MutableCollection<Int>>()
    val currentPositions = mutableMapOf(1 to Position(0,0), 2 to Position(0,0))

    fun addLine(wire : Int, line : String) {
        val direction = line[0]
        val amount = line.substring(1).toInt()

     when(direction) {
         'R' -> goRight(wire, amount)
         'L' -> goLeft(wire, amount)
         'U' -> goUp(wire, amount)
         'D' -> goDown(wire, amount)
     }

    }

    private fun goRight(wire: Int, amount: Int) {
        val y = currentPositions[wire]!!.y
        val startX = currentPositions[wire]!!.x
        val endX = startX + amount
        for(x in startX..endX step 1) {
            addWire(wire, Position(x, y))
        }
    }

    private fun goLeft(wire: Int, amount: Int) {
        val y = currentPositions[wire]!!.y
        val startX = currentPositions[wire]!!.x
        val endX = startX - amount
        for(x in startX downTo endX step 1) {
            addWire(wire, Position(x, y))
        }
    }

    private fun goUp(wire: Int, amount: Int) {
        val x = currentPositions[wire]!!.x
        val startY = currentPositions[wire]!!.y
        val endY = startY + amount
        for(y in startY..endY step 1) {
            addWire(wire, Position(x, y))
        }
    }

    private fun goDown(wire: Int, amount: Int) {
        val x = currentPositions[wire]!!.x
        val startY = currentPositions[wire]!!.y
        val endY = startY - amount
        for(y in startY downTo endY step 1) {
            addWire(wire, Position(x, y))
        }
    }

    private fun addWire(wire: Int, position: Position) {
        if (map[position] == null) {
            map[position] = mutableSetOf(wire)
        } else {
            map[position]!!.add(wire)
        }
        currentPositions[wire] = position
    }
}

data class Position(val x : Int, val y : Int)