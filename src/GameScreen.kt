import java.awt.Graphics2D

class GameScreen(private val labels: Element, private val field: GameField)
    : Element {

    private constructor(size: Size, labels: Labels):
            this(
                labels,
                GameField(
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
        field.draw(g)
    }

    override fun event(e: Event) {
        labels.event(e)
        field.event(e)
    }

    override fun tic() {
        labels.tic()
        field.tic()
    }
}