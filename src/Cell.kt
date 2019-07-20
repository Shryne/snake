import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

open class Cell(private val area: Area, private val color: Color) : Element {
    constructor(area: Area): this(area, Color.WHITE)
    constructor(pos: Pos, size: Size): this(Area(pos, size))

    final override fun draw(g: Graphics2D) {
        val previousColor = g.color
        area.apply(g::drawRect)
        g.color = color
        g.fillRect(
            area.x.toInt() + 1,
            area.y.toInt() + 1,
            area.w.toInt() - 2,
            area.h.toInt() - 2
        )
        g.color = previousColor
    }
}

class SnakeCell(area: Area) : Cell(area, Color.BLACK) {
    constructor(pos: Pos, size: Size): this(Area(pos, size))
}