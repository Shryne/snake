import java.awt.Color

typealias Target = (GridPos, Color) -> Unit

interface GridElement {
    fun drawOn(target: Target)
}