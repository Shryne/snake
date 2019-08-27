import java.awt.Color
import java.awt.Graphics2D

class Snake(
    startX: Int,
    startY: Int,
    private val gridSize: GridSize,
    private val onGrowth: () -> Unit = {}
) : GridElement {

    companion object {
        const val START_LENGTH = 3
    }

    private val direction = Direction()
    private var ticked = false

    private val posList = MutableList(START_LENGTH) {
        MutGridPos(startX, startY)
    }

    private val cells = MutableList(START_LENGTH) {
        Cell(
            posList[it],
            when (it) {
                0 -> Cell.HEAD_COLOR
                else -> Cell.BODY_COLOR
            }
        )
    }

    val length get() = cells.size

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
            cells.first().color = Cell.BODY_COLOR
            posList.reverse()
            cells.reverse()
            cells.first().color = Cell.HEAD_COLOR
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
        onGrowth()
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