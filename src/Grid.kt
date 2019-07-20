import java.awt.Graphics2D

class Grid(
    direction: Direction,
    private val area: Area,
    private val gridSize: GridSize,
    private val isVisible: Boolean = true
): Element {

    private val cellSize = Size(
        { area.w / gridSize.cols }, { area.h / gridSize.rows }
    )

    private val snake = Snake(
        direction,
        Pos(area.x + cellSize.w * 10, area.y + cellSize.h * 10),
        this,
        cellSize
    )

    /*
    private val array = Array(gridSize.rows) {
        row -> Array(gridSize.cols) {
            col -> Size({ area.w / gridSize.cols }, { area.h / gridSize.rows }).run {
                Cell(
                    Pos(
                        { area.x + row * this.w },
                        { area.y + col * this.h }
                    ),
                    this
                )
            }
        }
    }*/

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
        snake.draw(g)
    }
}

data class GridSize(val cols: Int, val rows: Int)

class GridPos(private val xNum: () -> Int, private val yNum: () -> Int) {
    val x get() = xNum()
    val y get() = yNum()
}