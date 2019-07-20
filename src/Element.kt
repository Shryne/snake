import java.awt.Graphics2D
import java.awt.event.KeyEvent

interface Element {
    fun draw(g: Graphics2D)
    fun event(e: Event)
    fun tic()
}