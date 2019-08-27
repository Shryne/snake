import java.awt.Graphics2D

class GameScreen(private val labels: Element, private val grid: Grid)
    : Element {

    private constructor(size: Size, scores: Scores):
        this(
            scores,
            Grid(
                Area(
                    { 0 },
                    { scores.height },
                    { size.w },
                    { size.h - scores.height }
                )
            ) {
                scores.score += 10
            }
        )

    constructor(size: Size): this(size, Scores(size))

    val snakeLength get() = grid.snakeLength

    override fun draw(g: Graphics2D) {
        labels.draw(g)
        grid.draw(g)
    }

    override fun event(e: Event) {
        labels.event(e)
        grid.event(e)
    }

    override fun tic() {
        labels.tic()
        grid.tic()
    }
}