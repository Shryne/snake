package shryne.gui.element

import shryne.Event
import shryne.area.*
import shryne.gui.Image
import java.awt.Graphics2D
import kotlin.math.floor
import kotlin.math.roundToInt

class Grid(
    private val area: Area,
    private val gridSize: GridSize = GridSize(20, 20),
    private val isVisible: Boolean = true,
    onGrowth: () -> Unit = {}
): Element {
    private val cellSize = Size(area.w / gridSize.cols, area.h / gridSize.rows)
    private val snake = Snake(10, 10, gridSize, onGrowth)

    private val allPositions = List(gridSize.rows) {
        x -> List(gridSize.cols) {
            y -> GridPos(x, y)
        }
    }

    private var food = Apple(
        snake.without(allPositions).random().random()
    )

    private var background = MutableList(gridSize.rows) { y ->
        MutableList(gridSize.cols) { x ->
            Image(
                "images/background1.png",
                Area(
                    area.x + cellSize.w * x,
                    Scalar { floor(area.y.toDouble() + y.toDouble() * cellSize.h.toDouble()) },
                    Scalar { cellSize.w.toDouble() },
                    Scalar { cellSize.h.toDouble() }
                )
            )
        }
    }

    val snakeLength get() = snake.length

    override fun draw(g: Graphics2D) {
        food.draw(g)
        background.forEach { it.forEach { it.draw(g) } }
        if (isVisible) {
            for (row in 0..gridSize.rows) {
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
    override fun tic() {
        snake.tic()
        if (snake.eats(food.pos)) {
            snake.grow()
            food = Apple(
                snake.without(allPositions).random().random()
            )
        }
    }
}

data class GridSize(val cols: Int, val rows: Int)

open class GridPos(private val xNum: () -> Int, private val yNum: () -> Int) {
    constructor(x: Int, y: Int): this({ x }, { y })

    open val x get() = xNum()
    open val y get() = yNum()
}

class MutGridPos(override var x: Int, override var y: Int)
    : GridPos({ x }, { y })