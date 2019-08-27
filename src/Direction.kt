
class Direction {
    var x: Int = 0
    var y: Int = 0

    fun apply(event: Event) {
        when (event) {
            Event.UP -> {
                x = 0
                y = -1
            }
            Event.DOWN -> {
                x = 0
                y = 1
            }
            Event.LEFT -> {
                x = -1
                y = 0
            }
            Event.RIGHT -> {
                x = 1
                y = 0 
            }
        }
    }
}