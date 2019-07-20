import java.awt.Graphics2D

class Grid(
    private val area: Area,
    private val gridSize: GridSize,
    private val isVisible: Boolean = true
): Element {

    private val cellSize = Size(
        { area.w / gridSize.cols }, { area.h / gridSize.rows }
    )

    private val snake = Snake(
        10,
        10,
        gridSize
    )

    override fun draw(g: Graphics2D) {
        if (isVisible) {
            for (row in 0 until gridSize.rows) {
                g.drawLine(
                    area.x.toInt(),
                    (area.y + row * cellSize.h.toDouble()).toInt(),
                    area.x.toInt() + area.w.toInt(),
                    (area.y + row * cellSize.h.toDouble()).toInt()
                )
            }
            for (col in 0..gridSize.cols) {
                g.drawLine(
                    (area.x + col * cellSize.w.toDouble()).toInt(),
                    area.y.toInt(),
                    (area.x + col * cellSize.w.toDouble()).toInt(),
                    area.y.toInt() + area.h.toInt()
                )
            }
        }
        snake.drawOn { gridPos, color ->
            val prev = g.color
            g.color = color
            g.fillRect(
                (area.x + gridPos.x * cellSize.w.toDouble()).toInt(),
                (area.y + gridPos.y * cellSize.h.toDouble()).toInt(),
                cellSize.w.toInt() + 1,
                cellSize.h.toInt() + 1
            )
            g.color = prev
        }
    }

    override fun event(e: Event) = snake.event(e)
    override fun tic() = snake.tic()
}

data class GridSize(val cols: Int, val rows: Int)

open class GridPos(private val xNum: () -> Int, private val yNum: () -> Int) {
    constructor(x: Int, y: Int): this({ x }, { y })

    open val x get() = xNum()
    open val y get() = yNum()
}

class MutGridPos(override var x: Int, override var y: Int)
    : GridPos({ x }, { y })