import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

open class Cell(private val pos: GridPos, private val color: Color) : GridElement {
    final override fun drawOn(target: Target) = target(pos, color)
}