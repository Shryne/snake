import java.awt.Color
import java.awt.Graphics2D

class Snake(startX: Int, startY: Int, private val gridSize: GridSize)
    : GridElement {

    private val direction = Direction()
    private var ticked = false

    private val posList = mutableListOf(
        MutGridPos(startX, startY),
        MutGridPos(startX, startY),
        MutGridPos(startX, startY)
    )

    private val cells = mutableListOf(
        Cell(
            posList.first(),
            Color.BLACK
        ),
        Cell(
            posList[1],
            Color.DARK_GRAY
        ),
        Cell(
            posList[2],
            Color.DARK_GRAY
        )
    )

    override fun drawOn(target: Target) = cells.forEach{ it.drawOn(target) }
    override fun event(event: Event) {
        if (!ticked) {
            direction.apply(event)
            ticked = true
        }
    }

    override fun tic() {
        if (posList.first().x + direction.x == posList[1].x &&
            posList.first().y + direction.y == posList[1].y) {
            posList.reverse()
        }
        for (i in (1 until posList.size).reversed()) {
            posList[i].x = posList[i - 1].x
            posList[i].y = posList[i - 1].y
        }
        val head = posList.first()
        head.x = (head.x + direction.x).inBounds(0, gridSize.cols - 1)
        head.y = (head.y + direction.y).inBounds(0, gridSize.rows - 1)
        ticked = false
    }

    fun eats(gridPos: GridPos) =
        posList.first().x == gridPos.x && posList.first().y == gridPos.y

    fun grow() {
        val last = posList.last()
        posList.add(MutGridPos(last.x, last.y))
        cells.add(
            Cell(
                posList.last(),
                Color.DARK_GRAY
            )
        )
    }

    fun without(posList: List<List<GridPos>>) : List<List<GridPos>> {
        val result = posList.map {
            it.toMutableList()
        }.toMutableList()
        this.posList.forEach { snakeCell ->
            result.forEach { row ->
                row.removeIf { it.x == snakeCell.x && it.y == snakeCell.y }
            }
            result.removeIf { it.isEmpty() }
        }
        return result
    }
}

private fun Int.inBounds(min: Int, max: Int) =
    when {
        this < min -> max
        max < this -> min
        else -> this
    }