import java.awt.Graphics2D

class GameScreen(private val labels: Element, private val grid: Grid)
    : Element {

    private constructor(size: Size, labels: Labels):
        this(
            labels,
            Grid(
                Area(
                    { 0 },
                    { labels.height },
                    { size.w },
                    { size.h - labels.height }
                )
            )
        )

    constructor(size: Size): this(size, Labels(size))

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