package shryne

enum class Event(val x: Int, val y: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    fun applyOn(direction: Direction) {
        direction.x = x
        direction.y = y
    }
}