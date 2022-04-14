package shryne.area

class Pos(val x: Number = 0, val y: Number = 0) {
    constructor(x: () -> Number, y: () -> Number) : this(Scalar(x), Scalar(y))
}