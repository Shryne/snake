import shryne.gui.element.GridElement
import java.awt.Color
import shryne.gui.element.Target
import shryne.Event
import shryne.gui.Image
import shryne.gui.element.Element
import shryne.gui.element.GridPos
import java.awt.Graphics2D

/**
 * A collectible for the snake. It will add a length of one to the snake and
 * it raises the score.
 */
class Apple(val pos: GridPos) : Element {
    private val image = Image("images/apple.png")

    override fun draw(g: Graphics2D) {
        image.draw(g)
    }

    override fun event(event: Event) = Unit
    override fun tic() = Unit
}