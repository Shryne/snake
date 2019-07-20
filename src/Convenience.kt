import java.lang.UnsupportedOperationException

class Pos(val x: Number = 0, val y: Number = 0) {
    constructor(x: () -> Number, y: () -> Number): this(Scalar(x), Scalar(y))
}

data class Size(val w: Number = 0, val h: Number = 0) {
    constructor(w: () -> Number, h: () -> Number): this(Scalar(w), Scalar(h))

}

data class Area(val pos: Pos = Pos(), val size: Size = Size()) {
    constructor(x: Number = 0, y: Number = 0, w: Number = 0, h: Number): this(Pos(x, y), Size(w, h))
    constructor(
        x: () -> Number, y: () -> Number, w: () -> Number, h: () -> Number
    ): this(Pos(x, y), Size(w, h))
    constructor(x: Number, y: Number, w: () -> Number, h: () -> Number): this(Pos(x, y), Size(w, h))

    val x get() = pos.x
    val y get() = pos.y
    val w get() = size.w
    val h get() = size.h

    fun apply(target: (Int, Int, Int, Int) -> Unit) = target(
        pos.x.toInt(), pos.y.toInt(), size.w.toInt(), size.h.toInt()
    )
}

class Scalar(private val value: () -> Number): Number() {
    override fun toByte() = value().toByte()
    override fun toChar() = value().toChar()
    override fun toDouble() = value().toDouble()
    override fun toFloat() = value().toFloat()
    override fun toInt() = value().toInt()
    override fun toLong() = value().toLong()
    override fun toShort() = value().toShort()
}

class NumberAddition(private val a: Number, private val b: Number): Number() {
    override fun toByte() = (a.toByte() + b.toByte()).toByte()
    override fun toChar() = a.toChar() + b.toInt()
    override fun toDouble() = a.toDouble() + b.toDouble()
    override fun toFloat() = a.toFloat() + b.toFloat()
    override fun toInt() = a.toInt() + b.toInt()
    override fun toLong() = a.toLong() + b.toLong()
    override fun toShort() = (a.toShort() + b.toShort()).toShort()
}

class NumberMultiplication(private val a: Number, private val b: Number): Number() {
    override fun toByte() = (a.toByte() * b.toByte()).toByte()
    override fun toChar() = throw UnsupportedOperationException("Don't do this!")
    override fun toDouble() = a.toDouble() * b.toDouble()
    override fun toFloat() = a.toFloat() * b.toFloat()
    override fun toInt() = a.toInt() * b.toInt()
    override fun toLong() = a.toLong() * b.toLong()
    override fun toShort() = (a.toShort() * b.toShort()).toShort()
}

operator fun Number.times(number: Number): Number {
    return NumberMultiplication(this, number)
}

operator fun Number.div(number: Number): Number {
    return Scalar { toDouble() / number.toDouble() }
}

operator fun Number.plus(number: Number): Number {
    return NumberAddition(this, number)
}

operator fun Number.minus(number: Number): Number {
    return Scalar { toDouble() - number.toDouble() }
}