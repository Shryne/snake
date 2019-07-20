import java.awt.Graphics2D

class GameField(
    private val grid: Grid,
    private val gridVisible: Boolean = true
) : Element {

    constructor(area: Area, gridSize: GridSize = GridSize(20, 20)):
            this(Grid(area, gridSize))

    override fun draw(g: Graphics2D) {
        if (gridVisible) {
            grid.draw(g)
        }
    }

    override fun event(e: Event) = grid.event(e)

    override fun tic() = grid.tic()
}