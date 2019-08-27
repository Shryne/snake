import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class Cell(private val pos: GridPos, var color: Color) : GridElement {
    companion object {
        val HEAD_COLOR = Color.BLACK
        val BODY_COLOR = Color.DARK_GRAY
    }

    override fun drawOn(target: Target) = target(pos, color)
    override fun event(event: Event) = Unit
    override fun tic() = Unit
}