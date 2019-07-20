import java.awt.Color
import java.awt.Graphics2D

class Snake(pos: GridPos): GridElement {

    private val cells = mutableListOf(
        Cell(pos, Color.BLACK)
    )

    override fun drawOn(target: Target) = cells.forEach{ it.drawOn(target) }
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