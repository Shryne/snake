package shryne.area

data class Size(val w: Number = 0, val h: Number = 0) {
    constructor(w: () -> Number, h: () -> Number) : this(Scalar(w), Scalar(h))

    override fun toString() = "(w: $w, h: $h)"
}