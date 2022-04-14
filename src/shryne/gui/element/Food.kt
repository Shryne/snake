import shryne.gui.element.GridElement
import java.awt.Color
import shryne.gui.element.Target
import shryne.Event

class Apple(val pos: GridPos) : GridElement {
    override fun drawOn(target: Target) = target(pos, Color.GREEN)
    override fun event(event: Event) = Unit
    override fun tic() = Unit
}