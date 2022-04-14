package shryne.gui.element

import shryne.Event
import shryne.area.Area
import shryne.area.Size
import shryne.area.minus
import java.awt.Graphics2D

/**
 * The content of the game.
 *
 * @param labels The labels (for example to show the score) to be shown.
 * @param grid The grid where the actual game happens.
 */
class GameScreen(
    private val labels: Element,
    private val grid: Grid
) : Element {

    /**
     * @param scores The scores to be shown.
     */
    private constructor(size: Size, scores: Scores) :
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

    constructor(size: Size) : this(size, Scores(size))

    /**
     * @param w The widht of the
     */
    constructor(w: Number, h: Number) : this(Size(w, h))

    /**
     * The current length of the snake (including the head).
     */
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