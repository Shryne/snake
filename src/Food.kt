import java.awt.Color

class Apple(val pos: GridPos) : GridElement {
    override fun drawOn(target: Target) = target(pos, Color.GREEN)
    override fun event(event: Event) = Unit
    override fun tic() = Unit
}