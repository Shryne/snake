import java.awt.Color
import java.awt.Graphics2D

class Snake(startX: Int, startY: Int): GridElement {
    private val direction = Direction()

    private val posList = mutableListOf(
        MutGridPos(startX, startY),
        MutGridPos(startX, startY),
        MutGridPos(startX, startY)
    )
    private val cells = mutableListOf(
        Cell(
            posList.first(),
            Color.BLACK
        ),
        Cell(
            posList[1],
            Color.GRAY
        ),
        Cell(
            posList[2],
            Color.GRAY
        )
    )

    override fun drawOn(target: Target) = cells.forEach{ it.drawOn(target) }
    override fun event(event: Event) = direction.apply(event)

    override fun tic() {
        for (i in (1 until posList.size).reversed()) {
            posList[i].x = posList[i - 1].x
            posList[i].y = posList[i - 1].y
        }
        posList.first().x += direction.x
        posList.first().y += direction.y
    }
}

class MutableInt(var value: Int) : () -> Int {
    override fun invoke() = value
}