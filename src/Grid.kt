import java.awt.Graphics2D

class Grid(
    private val area: Area,
    private val gridSize: GridSize,
    private val isVisible: Boolean = true
): Element {

    private val cellSize = Size(
        { area.w / gridSize.cols }, { area.h / gridSize.rows }
    )

    private val snake = Snake(10, 10)

    override fun draw(g: Graphics2D) {
        if (isVisible) {
            for (row in 0..gridSize.rows) {
                g.drawLine(
                    area.x.toInt(),
                    area.y.toInt() + row * cellSize.h.toInt(),
                    area.x.toInt() + area.w.toInt(),
                    area.y.toInt() + row * cellSize.h.toInt()
                )
            }
            for (col in 0..gridSize.cols) {
                g.drawLine(
                    area.x.toInt() + col * cellSize.w.toInt(),
                    area.y.toInt(),
                    area.x.toInt() + col * cellSize.w.toInt(),
                    area.y.toInt() + area.h.toInt()
                )
            }
        }
        snake.drawOn { gridPos, color ->
            val prev = g.color
            g.color = color
            g.fillRect(
                area.x.toInt() + gridPos.x() * cellSize.w.toInt(),
                area.y.toInt() + gridPos.y() * cellSize.h.toInt(),
                cellSize.w.toInt(),
                cellSize.h.toInt()
            )
            g.color = prev
        }
    }

    override fun event(e: Event) = snake.event(e)
    override fun tic() = snake.tic()
}

data class GridSize(val cols: Int, val rows: Int)

class GridPos(val x: () -> Int, val y: () -> Int) {
    constructor(x: Int, y: Int): this({ x }, { y })
}