package shryne.gui.element

import shryne.Event
import GridPos
import java.awt.Color

class Cell(private val pos: GridPos, var color: Color) : GridElement {
    companion object {
        val HEAD_COLOR = Color.BLACK
        val BODY_COLOR = Color.DARK_GRAY
    }

    override fun drawOn(target: Target) = target(pos, color)
    override fun event(event: Event) = Unit
    override fun tic() = Unit
}