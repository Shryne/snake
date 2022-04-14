package shryne.gui.element

import shryne.Event
import java.awt.Graphics2D

interface Element {
    fun draw(g: Graphics2D)
    fun event(e: Event)
    fun tic()
}