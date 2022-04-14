package shryne.area

import java.lang.UnsupportedOperationException

class NumberAddition(private val a: Number, private val b: Number) : Number() {
    override fun toByte() = (a.toByte() + b.toByte()).toByte()
    override fun toChar() = a.toChar() + b.toInt()
    override fun toDouble() = a.toDouble() + b.toDouble()
    override fun toFloat() = a.toFloat() + b.toFloat()
    override fun toInt() = a.toInt() + b.toInt()
    override fun toLong() = a.toLong() + b.toLong()
    override fun toShort() = (a.toShort() + b.toShort()).toShort()
}

class NumberMultiplication(private val a: Number, private val b: Number) :
    Number() {
    override fun toByte() = (a.toByte() * b.toByte()).toByte()
    override fun toChar() =
        throw UnsupportedOperationException("Don't do this!")

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