package shryne.area

data class Area(val pos: Pos = Pos(), val size: Size = Size()) {
    constructor(x: Number = 0, y: Number = 0, w: Number = 0, h: Number) : this(
        Pos(x, y),
        Size(w, h)
    )

    constructor(
        x: () -> Number,
        y: () -> Number,
        w: () -> Number,
        h: () -> Number
    ) : this(Pos(x, y), Size(w, h))

    constructor(x: Number, y: Number, w: () -> Number, h: () -> Number) : this(
        Pos(x, y),
        Size(w, h)
    )

    val x get() = pos.x
    val y get() = pos.y
    val w get() = size.w
    val h get() = size.h

    fun applyOn(target: (Int, Int, Int, Int) -> Unit) = target(
        pos.x.toInt(), pos.y.toInt(), size.w.toInt(), size.h.toInt()
    )

    override fun toString() = "[$pos, $size]"
}