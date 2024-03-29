package shryne.gui.element

import shryne.Event
import java.awt.Color

typealias Target = (GridPos, Color) -> Unit

interface GridElement {
    fun drawOn(target: Target)
    fun event(event: Event)
    fun tic()
}