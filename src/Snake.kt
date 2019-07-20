import java.awt.Graphics2D

class Snake(
    private val direction: Direction,
    pos: Pos, grid: Grid,
    private val partSize: Size
): Element {

    private val x = MutableScalar(0)
    private val y = MutableScalar(0)

    private val cells = mutableListOf(
        SnakeCell(
            Pos(
                pos.x + x,
                pos.y + y
            ),
            partSize
        )
    )

    private var startTime = System.currentTimeMillis()


    override fun draw(g: Graphics2D) {
        if (System.currentTimeMillis() - startTime > 600) {
            x.value += direction.x * partSize.w.toInt()
            y.value += direction.y * partSize.h.toInt()
            startTime = System.currentTimeMillis()
        }
        cells.forEach{ it.draw(g) }
    }
}

class MutableScalar(var value: Int) : Number() {
    override fun toByte() = value.toByte()
    override fun toChar() = value.toChar()
    override fun toDouble() = value.toDouble()
    override fun toFloat() = value.toFloat()
    override fun toInt() = value
    override fun toLong() = value.toLong()
    override fun toShort() = value.toShort()
}