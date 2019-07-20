import java.awt.Graphics2D

class GameScreen(private val labels: Element, private val field: GameField) : Element {
    private constructor(size: Size, labels: Labels, direction: Direction):
            this(
                labels,
                GameField(
                    direction,
                    Area(
                        { 0 },
                        { labels.height },
                        { size.w },
                        { size.h - labels.height }
                    )
                )
            )

    constructor(size: Size, direction: Direction): this(size, Labels(size), direction)

    override fun draw(g: Graphics2D) {
        labels.draw(g)
        field.draw(g)
    }
}