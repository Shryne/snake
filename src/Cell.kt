import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class Cell(private val pos: GridPos, private val color: Color) : GridElement {
    override fun drawOn(target: Target) = target(pos, color)
    override fun event(event: Event) = Unit
    override fun tic() = Unit
}