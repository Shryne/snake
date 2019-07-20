import java.awt.Color
import java.awt.Graphics2D

class Snake(startX: Int, startY: Int): GridElement {
    private val direction = Direction()
    private val x = MutableInt(startX)
    private val y = MutableInt(startY)

    private val cells = mutableListOf(
        Cell(
            GridPos(x, y),
            Color.BLACK
        )
    )

    override fun drawOn(target: Target) = cells.forEach{ it.drawOn(target) }
    override fun event(event: Event) = direction.apply(event)

    override fun tic() {
        x.value += direction.x
        y.value += direction.y
    }
}

class MutableInt(var value: Int) : () -> Int {
    override fun invoke() = value
}