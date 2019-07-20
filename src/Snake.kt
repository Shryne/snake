import java.awt.Color
import java.awt.Graphics2D

class Snake(startX: Int, startY: Int, private val gridSize: GridSize)
    : GridElement {

    private val direction = Direction()

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
            Color.GRAY
        ),
        Cell(
            posList[2],
            Color.GRAY
        )
    )

    override fun drawOn(target: Target) = cells.forEach{ it.drawOn(target) }
    override fun event(event: Event) = direction.apply(event)

    override fun tic() {
        for (i in (1 until posList.size).reversed()) {
            posList[i].x = posList[i - 1].x
            posList[i].y = posList[i - 1].y
        }
        val head = posList.first()
        head.x = (head.x + direction.x).inBounds(0, gridSize.cols - 1)
        head.y = (head.y + direction.y).inBounds(0, gridSize.rows - 1)
    }
}

private fun Int.inBounds(min: Int, max: Int) =
    when {
        this < min -> max
        max < this -> min
        else -> this
    }

class MutableInt(var value: Int) : () -> Int {
    override fun invoke() = value
}