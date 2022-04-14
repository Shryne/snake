package area

class Scalar(private val value: () -> Number) : Number() {
    override fun toByte() = value().toByte()
    override fun toChar() = value().toChar()
    override fun toDouble() = value().toDouble()
    override fun toFloat() = value().toFloat()
    override fun toInt() = value().toInt()
    override fun toLong() = value().toLong()
    override fun toShort() = value().toShort()
}